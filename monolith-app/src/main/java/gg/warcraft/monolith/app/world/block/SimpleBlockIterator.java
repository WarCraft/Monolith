package gg.warcraft.monolith.app.world.block;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.math.Vector3f;
import gg.warcraft.monolith.api.world.BlockLocation;
import gg.warcraft.monolith.api.world.Location;
import gg.warcraft.monolith.api.world.block.Block;
import gg.warcraft.monolith.api.world.block.BlockIterator;
import gg.warcraft.monolith.api.world.service.WorldQueryService;

public class SimpleBlockIterator implements BlockIterator {
    private final WorldQueryService worldQueryService;
    private final Location origin;
    private final float maxDistance;
    private final Vector3f direction;

    private Location scanLocation;
    private float distance;
    private BlockLocation currentBlockLocation;
    private BlockLocation nextBlockLocation;

    @Inject
    public SimpleBlockIterator(WorldQueryService worldQueryService,
                               @Assisted("origin") Location origin, @Assisted("target") Location target) {
        this.worldQueryService = worldQueryService;
        this.origin = origin;
        this.maxDistance = origin.getTranslation().dist(target.getTranslation());
        this.direction = target.sub(origin).getTranslation().normalize();
        this.scanLocation = origin;
        this.currentBlockLocation = null;
        this.nextBlockLocation = origin.toBlockLocation();
    }

    BlockLocation calculateNext() {
        Vector3f delta;
        if (distance + 1 < maxDistance) {
            delta = direction;
            distance += 1;

            scanLocation = scanLocation.add(delta);
            if (scanLocation.toBlockLocation().equals(currentBlockLocation)) {
                return calculateNext();
            }
            return scanLocation.toBlockLocation();
        } else {
            delta = direction.mul(maxDistance - distance);
            distance = maxDistance;

            scanLocation = scanLocation.add(delta);
            if (scanLocation.toBlockLocation().equals(currentBlockLocation)) {
                return null;
            }
            return scanLocation.toBlockLocation();
        }
    }

    @Override
    public Location calculateIntersection() {
        return currentBlockLocation.toLocation().add(0.5f, 0.5f, 0.5f);
        // FIXME
//        System.out.println("DEBUG Calculating intersection for " + currentBlockLocation);
//        Vector3f blockMinimumCorner = currentBlockLocation.toLocation().toVector();
//        AABBf blockBoundingBox = new AABBf(blockMinimumCorner, blockMinimumCorner.add(1, 1, 1, new Vector3f()));
//        Rayf iteratorRay = new Rayf(origin.toVector(), direction);
//        Vector2f intersectionResult = new Vector2f();
//        if (blockBoundingBox.intersectRay(iteratorRay, intersectionResult)) {
//            float nearIntersectionScalar = intersectionResult.x;
//            return origin.add(direction.mul(nearIntersectionScalar, new Vector3f()));
//        } else {
//            throw new IllegalStateException("Failed to calculate intersection for current block location in block " +
//                    "iterator, this should not be possible.");
//        }
    }

    @Override
    public boolean hasNext() {
        return nextBlockLocation != null;
    }

    @Override
    public Block next() {
        currentBlockLocation = nextBlockLocation;
        nextBlockLocation = calculateNext();
        Block block = worldQueryService.getBlockAt(currentBlockLocation);
        return block;
    }
}
