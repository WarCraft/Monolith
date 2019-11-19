package gg.warcraft.monolith.app.entity;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityIntersection;
import gg.warcraft.monolith.api.entity.EntityTarget;
import gg.warcraft.monolith.api.entity.EntityUtils;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.block.BlockIntersection;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import org.joml.AABBf;
import org.joml.Intersectionf;
import org.joml.LineSegmentf;
import org.joml.Vector2f;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class DefaultEntityUtils implements EntityUtils {
    private final EntityQueryService entityQueryService;
    private final BlockUtils blockUtils;

    @Inject
    public DefaultEntityUtils(EntityQueryService entityQueryService, BlockUtils blockUtils) {
        this.entityQueryService = entityQueryService;
        this.blockUtils = blockUtils;
    }

    @Override
    public EntityIntersection intersectEntity(Location origin, Location target, Predicate<Entity> ignore) {
        Vector3f originVector = origin.translation();
        Vector3f targetVector = target.translation();
        org.joml.Vector3f jomlOriginVec = new org.joml.Vector3f(originVector.x(),
                originVector.y(), originVector.z());
        org.joml.Vector3f jomlTargetVec = new org.joml.Vector3f(targetVector.x(),
                targetVector.y(), targetVector.z());
        AABBf boundingBox = new AABBf(jomlOriginVec, jomlTargetVec);
        boundingBox = boundingBox.correctBounds();

        float deltaX = (boundingBox.maxX - boundingBox.minX) * 0.5f;
        float deltaY = (boundingBox.maxY - boundingBox.minY) * 0.5f;
        float deltaZ = (boundingBox.maxZ - boundingBox.minZ) * 0.5f;

        float centerX = boundingBox.minX + deltaX;
        float centerY = boundingBox.minY + deltaY;
        float centerZ = boundingBox.minZ + deltaZ;

        Location center = new Location(origin.world(), new Vector3f(centerX, centerY, centerZ), new Vector3f());
        LineSegmentf intersectionLine = new LineSegmentf(jomlOriginVec, jomlTargetVec);
        List<Entity> nearbyEntities = entityQueryService.getNearbyEntities(center, deltaX, deltaY, deltaZ);
        float closestIntersectionScalar = Float.MAX_VALUE;
        Entity closestIntersectedEntity = null;
        for (Entity nearbyEntity : nearbyEntities) {
            if (ignore.test(nearbyEntity)) {
                continue;
            }

            Vector2f intersectionResult = new Vector2f();
            int result = nearbyEntity.getBoundingBox().intersectLineSegment(intersectionLine, intersectionResult);
            if (result != Intersectionf.OUTSIDE) {
                if (intersectionResult.x < closestIntersectionScalar) {
                    closestIntersectionScalar = intersectionResult.x;
                    closestIntersectedEntity = nearbyEntity;
                }
            }
        }

        if (closestIntersectedEntity != null) {
            Vector3f distanceAlongRay = targetVector.subtract(originVector).multiply(closestIntersectionScalar);
            Vector3f intersection = originVector.add(distanceAlongRay);
            Location intersectionLocation = new Location(origin.world(), intersection, new Vector3f());
            return new SimpleEntityIntersection(closestIntersectedEntity, intersectionLocation);
        }
        return null;
    }

    @Override
    public EntityTarget calculateTarget(UUID entityId, float range, boolean ignoreFriendlies) {
        Entity entity = entityQueryService.getEntity(entityId);
        Location origin = entity.getEyeLocation();
        Vector3f direction = entity.getEyeLocation().rotation();
        Location target = origin.add(direction.multiply(range));

        BlockIntersection blockIntersection = blockUtils.intersectBlock(origin, target, block -> !block.solid());

        Location correctedTarget = blockIntersection != null && blockIntersection.getLocation() != null
                ? blockIntersection.getLocation()
                : target;
        Predicate<Entity> ignore = ignoreFriendlies
                ? e -> e.getTeam() == entity.getTeam()
                : e -> false;
        EntityIntersection entityIntersection = intersectEntity(origin, correctedTarget, ignore);

        return new SimpleEntityTarget(blockIntersection, entityIntersection);
    }
}
