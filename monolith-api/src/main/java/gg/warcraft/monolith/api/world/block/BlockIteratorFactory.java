package gg.warcraft.monolith.api.world.block;

import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.world.Location;

public interface BlockIteratorFactory {

    BlockIterator createBlockIterator(@Assisted("origin") Location origin, @Assisted("target") Location target);
}
