package gg.warcraft.monolith.spigot.world;

import gg.warcraft.monolith.api.world.Sound;
import gg.warcraft.monolith.api.world.SoundCategory;

public class SpigotSoundMapper {

    public org.bukkit.Sound map(Sound sound) {
        return org.bukkit.Sound.valueOf(sound.name());
    }

    public Sound map(org.bukkit.Sound sound) {
        return Sound.valueOf(sound.name());
    }

    public org.bukkit.SoundCategory map(SoundCategory category) {
        return org.bukkit.SoundCategory.valueOf(category.name());
    }

    public SoundCategory map(org.bukkit.SoundCategory category) {
        return SoundCategory.valueOf(category.name());
    }
}
