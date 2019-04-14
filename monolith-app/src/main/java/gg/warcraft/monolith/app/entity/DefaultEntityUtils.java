package gg.warcraft.monolith.app.entity;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityIntersection;
import gg.warcraft.monolith.api.entity.EntityTarget;
import gg.warcraft.monolith.api.entity.EntityUtils;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.world.block.BlockIntersection;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import gg.warcraft.monolith.api.world.location.Location;
import gg.warcraft.monolith.api.world.location.LocationFactory;
import gg.warcraft.monolith.api.world.location.OrientedLocation;
import org.joml.AABBf;
import org.joml.Intersectionf;
import org.joml.LineSegmentf;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class DefaultEntityUtils implements EntityUtils {
    private final EntityQueryService entityQueryService;
    private final LocationFactory locationFactory;
    private final BlockUtils blockUtils;
    private final BlockTypeUtils blockTypeUtils;

    @Inject
    public DefaultEntityUtils(EntityQueryService entityQueryService, LocationFactory locationFactory,
                              BlockUtils blockUtils, BlockTypeUtils blockTypeUtils) {
        this.entityQueryService = entityQueryService;
        this.locationFactory = locationFactory;
        this.blockUtils = blockUtils;
        this.blockTypeUtils = blockTypeUtils;
    }

    @Override
    public EntityIntersection intersectEntity(Location origin, Location target, Predicate<Entity> ignore) {
        Vector3fc originVector = origin.toVector();
        Vector3fc targetVector = target.toVector();
        AABBf boundingBox = new AABBf(originVector, targetVector);
        boundingBox = boundingBox.correctBounds();

        float deltaX = (boundingBox.maxX - boundingBox.minX) * 0.5f;
        float deltaY = (boundingBox.maxY - boundingBox.minY) * 0.5f;
        float deltaZ = (boundingBox.maxZ - boundingBox.minZ) * 0.5f;

        float centerX = boundingBox.minX + deltaX;
        float centerY = boundingBox.minY + deltaY;
        float centerZ = boundingBox.minZ + deltaZ;

        Location center = locationFactory.createLocation(origin.getWorld(), centerX, centerY, centerZ);
        LineSegmentf intersectionLine = new LineSegmentf(originVector, targetVector);
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
            Vector3fc distanceAlongRay = targetVector.min(originVector, new Vector3f()).mul(closestIntersectionScalar);
            Vector3fc intersection = originVector.add(distanceAlongRay, new Vector3f());
            Location intersectionLocation = locationFactory.createLocation(origin.getWorld(),
                    intersection.x(), intersection.y(), intersection.z());
            return new SimpleEntityIntersection(closestIntersectedEntity, intersectionLocation);
        }
        return null;
    }

    @Override
    public EntityTarget calculateTarget(UUID entityId, float range, boolean ignoreFriendlies) {
        Entity entity = entityQueryService.getEntity(entityId);
        OrientedLocation origin = entity.getEyeLocation();
        Vector3fc direction = origin.toDirection();
        Location target = origin.add(direction.mul(range, new Vector3f()));

        BlockIntersection blockIntersection = blockUtils.intersectBlock(origin, target, blockTypeUtils.getNonSolids());

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
