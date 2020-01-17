package gg.warcraft.monolith.spigot;

import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
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
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.menu.MenuService;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.WorldService;
import gg.warcraft.monolith.api.world.block.backup.BlockBackupService;
import gg.warcraft.monolith.api.world.item.ItemService;
import gg.warcraft.monolith.app.AbstractMonolithModule;
import gg.warcraft.monolith.app.effect.particle.MultiParticle;
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
import gg.warcraft.monolith.spigot.entity.SpigotEntityEventMapper;
import gg.warcraft.monolith.spigot.menu.SpigotMenuService;
import gg.warcraft.monolith.spigot.world.SpigotLocationMapper;
import gg.warcraft.monolith.spigot.world.block.SpigotBlockMapper;
import gg.warcraft.monolith.spigot.world.block.backup.SpigotBlockBackupService;
import gg.warcraft.monolith.spigot.world.item.SpigotItemMapper;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class SpigotMonolithModule extends AbstractMonolithModule {
    private final Plugin plugin;

    public SpigotMonolithModule(String configurationService, String gitHubAccount, String gitHubRepository,
                                String persistenceService, String redisHost, int redisPort,
                                float baseHealth, World buildRepositoryWorld,
                                Vector3i buildRepositoryMinimumCorner, Vector3i buildRepositoryMaximumCorner,
                                Plugin plugin) {
        super(configurationService, gitHubAccount, gitHubRepository, persistenceService, redisHost, redisPort,
                baseHealth, buildRepositoryWorld, buildRepositoryMinimumCorner, buildRepositoryMaximumCorner);
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        super.configure();
        configureBukkit();
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
        bind(MenuService.class).to(SpigotMenuService.class);
        expose(MenuService.class);
    }

    private void configureWorld() {
        bind(BlockBackupService.class).toProvider(Implicits::blockBackupService);
        expose(BlockBackupService.class);

        bind(WorldService.class).toProvider(Implicits::worldService);
        expose(WorldService.class); // TODO remove when app no longer depends on it
        bind(SpigotLocationMapper.class).toProvider(Implicits::locationMapper);
        expose(SpigotLocationMapper.class); // TODO remove when app no longer depends on it
        bind(SpigotItemMapper.class).toProvider(Implicits::itemMapper);
        expose(SpigotItemMapper.class); // TODO remove when app no longer depends on it
        bind(SpigotBlockMapper.class).toProvider(Implicits::blockMapper);
        expose(SpigotBlockMapper.class); // TODO remove when app no longer depends on it
        bind(ItemService.class).toProvider(Implicits::itemService);
        expose(ItemService.class); // TODO remove when app no longer depends on it
    }

    private void configureMapper() {
        bind(SpigotEntityAdapter.class);
        expose(SpigotEntityAdapter.class);

        bind(SpigotEntityEventMapper.class);
        expose(SpigotEntityEventMapper.class);
    }
}
