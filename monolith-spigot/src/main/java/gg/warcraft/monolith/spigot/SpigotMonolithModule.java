package gg.warcraft.monolith.spigot;

import com.google.inject.Key;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import gg.warcraft.monolith.api.command.service.CommandServerAdapter;
import gg.warcraft.monolith.api.core.AuthorizationService;
import gg.warcraft.monolith.api.core.PluginLogger;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.effect.Particle;
import gg.warcraft.monolith.api.effect.ParticleFactory;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.player.PlayerServerData;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingServerAdapter;
import gg.warcraft.monolith.api.entity.player.service.PlayerServerAdapter;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.api.menu.service.MenuServerAdapter;
import gg.warcraft.monolith.api.world.WorldType;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupCommandService;
import gg.warcraft.monolith.api.world.service.WorldServerAdapter;
import gg.warcraft.monolith.app.AbstractMonolithModule;
import gg.warcraft.monolith.app.effect.particle.MultiParticle;
import gg.warcraft.monolith.spigot.command.SpigotCommandAdapter;
import gg.warcraft.monolith.spigot.core.SpigotAuthorizationService;
import gg.warcraft.monolith.spigot.core.SpigotTaskService;
import gg.warcraft.monolith.spigot.effect.particle.ColorParticle;
import gg.warcraft.monolith.spigot.effect.particle.SimpleParticle;
import gg.warcraft.monolith.spigot.effect.particle.SpeedParticle;
import gg.warcraft.monolith.spigot.entity.SpigotEntityData;
import gg.warcraft.monolith.spigot.entity.SpigotEntityDataFactory;
import gg.warcraft.monolith.spigot.entity.player.SpigotPlayerData;
import gg.warcraft.monolith.spigot.entity.player.SpigotPlayerDataFactory;
import gg.warcraft.monolith.spigot.entity.player.hiding.SpigotPlayerHidingAdapter;
import gg.warcraft.monolith.spigot.entity.player.service.SpigotPlayerAdapter;
import gg.warcraft.monolith.spigot.entity.service.SpigotEntityAdapter;
import gg.warcraft.monolith.spigot.event.SpigotEntityEventMapper;
import gg.warcraft.monolith.spigot.menu.service.SpigotMenuAdapter;
import gg.warcraft.monolith.spigot.world.Overworld;
import gg.warcraft.monolith.spigot.world.TheEnd;
import gg.warcraft.monolith.spigot.world.TheNether;
import gg.warcraft.monolith.spigot.world.block.backup.service.SpigotBlockBackupCommandService;
import gg.warcraft.monolith.spigot.world.service.SpigotWorldAdapter;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.joml.Vector3ic;

import java.util.logging.Logger;

public class SpigotMonolithModule extends AbstractMonolithModule {
    private final Plugin plugin;
    private final String overworldName;
    private final String theNetherName;
    private final String theEndName;

    public SpigotMonolithModule(String configurationService, String gitHubAccount, String gitHubRepository,
                                String persistenceService, String redisHost, int redisPort,
                                float baseHealth, WorldType buildRepositoryWorld,
                                Vector3ic buildRepositoryMinimumCorner, Vector3ic buildRepositoryMaximumCorner,
                                Plugin plugin, String overworldName, String theNetherName, String theEndName) {
        super(configurationService, gitHubAccount, gitHubRepository, persistenceService, redisHost, redisPort,
                baseHealth, buildRepositoryWorld, buildRepositoryMinimumCorner, buildRepositoryMaximumCorner);
        this.plugin = plugin;
        this.overworldName = overworldName;
        this.theNetherName = theNetherName;
        this.theEndName = theEndName;
    }

    @Override
    protected void configure() {
        super.configure();
        configureBukkit();
        configureCommand();
        configureCore();
        configureEffect();
        configureEntity();
        configureMenu();
        configureWorld();
        configureMapper();
    }

    private void configureBukkit() {
        bind(Plugin.class).toInstance(plugin);

        bind(Logger.class).annotatedWith(PluginLogger.class).toProvider(plugin::getLogger);

        bind(Server.class).toProvider(plugin::getServer);
        expose(Server.class);

        // Bukkit world bindings
        bind(World.class).annotatedWith(Overworld.class).toProvider(() -> plugin.getServer().getWorld(overworldName));
        expose(Key.get(World.class, Overworld.class));

        bind(World.class).annotatedWith(TheNether.class).toProvider(() -> plugin.getServer().getWorld(theNetherName));
        expose(Key.get(World.class, TheNether.class));

        bind(World.class).annotatedWith(TheEnd.class).toProvider(() -> plugin.getServer().getWorld(theEndName));
        expose(Key.get(World.class, TheEnd.class));
    }

    private void configureCommand() {
        bind(CommandServerAdapter.class).to(SpigotCommandAdapter.class);
        expose(CommandServerAdapter.class);
    }

    private void configureCore() {
        bind(AuthorizationService.class).to(SpigotAuthorizationService.class);
        expose(AuthorizationService.class);

        bind(TaskService.class).to(SpigotTaskService.class);
        expose(TaskService.class);
    }

    private void configureEffect() {
        install(new FactoryModuleBuilder()
                .implement(Particle.class, Names.named("simple"), SimpleParticle.class)
                .implement(Particle.class, Names.named("color"), ColorParticle.class)
                .implement(Particle.class, Names.named("speed"), SpeedParticle.class)
                .implement(Particle.class, Names.named("multi"), MultiParticle.class)
                .build(ParticleFactory.class));
        expose(ParticleFactory.class);
    }

    private void configureEntity() {
        // Entity server adapters
        bind(EntityServerAdapter.class).to(SpigotEntityAdapter.class);
        expose(EntityServerAdapter.class);

        bind(PlayerServerAdapter.class).to(SpigotPlayerAdapter.class);
        expose(PlayerServerAdapter.class);

        bind(PlayerHidingServerAdapter.class).to(SpigotPlayerHidingAdapter.class);
        expose(PlayerHidingServerAdapter.class);

        // Entity factory bindings
        install(new FactoryModuleBuilder()
                .implement(EntityServerData.class, SpigotEntityData.class)
                .build(SpigotEntityDataFactory.class));
        expose(SpigotEntityDataFactory.class);

        install(new FactoryModuleBuilder()
                .implement(PlayerServerData.class, SpigotPlayerData.class)
                .build(SpigotPlayerDataFactory.class));
        expose(SpigotPlayerDataFactory.class);
    }

    private void configureMenu() {
        bind(MenuServerAdapter.class).to(SpigotMenuAdapter.class);
        expose(MenuServerAdapter.class);
    }

    private void configureWorld() {
        bind(BlockBackupCommandService.class).to(SpigotBlockBackupCommandService.class);
        expose(BlockBackupCommandService.class);

        bind(WorldServerAdapter.class).to(SpigotWorldAdapter.class);
        expose(WorldServerAdapter.class);
    }

    private void configureMapper() {
        bind(SpigotEntityAdapter.class);
        expose(SpigotEntityAdapter.class);

        bind(SpigotEntityEventMapper.class);
        expose(SpigotEntityEventMapper.class);
    }
}
