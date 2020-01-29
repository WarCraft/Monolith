package gg.warcraft.monolith.spigot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import gg.warcraft.monolith.api.Monolith;
import gg.warcraft.monolith.api.core.Duration;
import gg.warcraft.monolith.api.core.event.EventService;
import gg.warcraft.monolith.api.core.ServerShutdownEvent;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.app.core.handler.DailyTickHandler;
import gg.warcraft.monolith.app.core.handler.DebugStickHandler;
import gg.warcraft.monolith.app.entity.attribute.handler.AttributesInitializationHandler;
import gg.warcraft.monolith.app.entity.handler.EntityProfileInitializationHandler;
import gg.warcraft.monolith.app.entity.player.handler.PlayerProfileInitializationHandler;
import gg.warcraft.monolith.app.entity.player.handler.PlayerProfileUpdateHandler;
import gg.warcraft.monolith.app.entity.player.hiding.handler.PlayerHidingHandler;
import gg.warcraft.monolith.app.world.portal.handler.PortalEntryTaskHandler;
import gg.warcraft.monolith.spigot.entity.handler.EntityRemovalHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.flywaydb.core.Flyway;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MonolithPlugin extends JavaPlugin {
    private Injector injector;
    private EventService eventService;
    private TaskService taskService;

    void initializeInjector() {
        List<Module> monolithModules = Monolith.getModules();
        List<String> monolithModuleNames = monolithModules.stream()
                .map(module -> module.getClass().getSimpleName())
                .collect(Collectors.toList());
        getLogger().info("Found " + monolithModules.size() + " Monolith modules: " + monolithModuleNames);
        Injector injector = Guice.createInjector(monolithModules);
        new Monolith(injector);
    }

    void initializeMonolithHandlers() {
        AttributesInitializationHandler attributesInitializationHandler =
                injector.getInstance(AttributesInitializationHandler.class);
        eventService.subscribe(attributesInitializationHandler);

        EntityProfileInitializationHandler entityProfileInitializationHandler =
                injector.getInstance(EntityProfileInitializationHandler.class);
        eventService.subscribe(entityProfileInitializationHandler);

        PlayerProfileInitializationHandler playerProfileInitializationHandler =
                injector.getInstance(PlayerProfileInitializationHandler.class);
        eventService.subscribe(playerProfileInitializationHandler);

        TimeUtils timeUtils = injector.getInstance(TimeUtils.class);
        EntityRemovalHandler entityRemovalHandler = injector.getInstance(EntityRemovalHandler.class);
        taskService.runTask(entityRemovalHandler, Duration.ofSeconds(10), Duration.ofSeconds(10));

        PlayerProfileUpdateHandler playerProfileUpdateHandler = injector.getInstance(PlayerProfileUpdateHandler.class);
        taskService.runTask(playerProfileUpdateHandler, Duration.oneTick(), Duration.oneTick());

        PlayerHidingHandler playerHidingHandler = injector.getInstance(PlayerHidingHandler.class);
        eventService.subscribe(playerHidingHandler);

        PortalEntryTaskHandler portalEntryTaskHandler = injector.getInstance(PortalEntryTaskHandler.class);
        taskService.runTask(portalEntryTaskHandler, Duration.ofMillis(250), Duration.ofMillis(250));

        DailyTickHandler dailyTickHandler = injector.getInstance(DailyTickHandler.class);
        taskService.runTask(dailyTickHandler, Duration.ofMillis(1900), Duration.ofMillis(1900));

        DebugStickHandler debugStickHandler = injector.getInstance(DebugStickHandler.class);
        eventService.subscribe(debugStickHandler);
    }

    void initializeSpigotEventMappers() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(Implicits.combatEventMapper(), this);
        pluginManager.registerEvents(Implicits.entityEventMapper(), this);
        pluginManager.registerEvents(Implicits.itemEventMapper(), this);
        pluginManager.registerEvents(Implicits.menuEventMapper(), this);
        pluginManager.registerEvents(Implicits.playerEventMapper(), this);
        pluginManager.registerEvents(Implicits.worldEventMapper(), this);
    }

    @Override
    public void onLoad() {
        new ImplicitsJavaHack().doTheThing(getServer(), this);

        saveDefaultConfig();
        FileConfiguration localConfig = getConfig();

        Flyway flyway = Flyway
                .configure(getClassLoader())
                .dataSource("jdbc:sqlite:" + getDataFolder().getAbsolutePath() + File.separator + "db.sqlite",
                        null, null)
                .load();
        int migrations = flyway.migrate();
        System.out.println("Applied " + migrations + " new data schema migrations");


        String configurationService = localConfig.getString("configurationService");
        String gitHubAccount = localConfig.getString("gitHubAccount");
        String gitHubRepository = localConfig.getString("gitHubRepository");

        String persistenceService = localConfig.getString("persistenceService");
        String redisHost = localConfig.getString("redisHost");
        int redisPort = localConfig.getInt("redisPort");

        float baseHealth = (float) localConfig.getDouble("baseHealth");

        String buildRepositoryWorldString = localConfig.getString("buildRepository.world");
        World buildRepositoryWorld = World.valueOf(buildRepositoryWorldString);
        Vector3i buildRepositoryMinimumCorner = new Vector3i(
                localConfig.getInt("buildRepository.minimumCorner.x"),
                localConfig.getInt("buildRepository.minimumCorner.y"),
                localConfig.getInt("buildRepository.minimumCorner.z"));
        Vector3i buildRepositoryMaximumCorner = new Vector3i(
                localConfig.getInt("buildRepository.maximumCorner.x"),
                localConfig.getInt("buildRepository.maximumCorner.y"),
                localConfig.getInt("buildRepository.maximumCorner.z"));

        // TODO remove
        String overworldName = localConfig.getString("worldDirectoryName");
        String netherName = localConfig.getString("netherDirectoryName");
        String theEndName = localConfig.getString("endDirectoryName");

        Module spigotMonolithModule = new SpigotMonolithModule(
                configurationService, gitHubAccount, gitHubRepository,
                persistenceService, redisHost, redisPort,
                baseHealth, buildRepositoryWorld,
                buildRepositoryMinimumCorner, buildRepositoryMaximumCorner,
                this);
        Monolith.registerModule(spigotMonolithModule);
    }

    @Override
    public void onEnable() {
        FileConfiguration localConfig = getConfig();

        boolean isFirstTimeSetup = localConfig.getBoolean("firstTimeSetup");
        if (isFirstTimeSetup) {
            getLogger().severe("Monolith has not been configured yet, shutting down server.");
            getLogger().severe("If you have finished configuration make sure to set firstTimeSetup to false.");
            getServer().shutdown();
            return;
        }

        boolean maintenance = localConfig.getBoolean("maintenance");
        if (maintenance) {
            // TODO setup maintenance login and session checker
        }

        initializeInjector();
        injector = Monolith.getInstance().getInjector();
        eventService = injector.getInstance(EventService.class);
        taskService = injector.getInstance(TaskService.class);

        initializeMonolithHandlers();
        initializeSpigotEventMappers();

        getLogger().info("Initializing build repository, this might take a little bit..");
        BlockBuildCommandService blockBuildCommandService = injector.getInstance(BlockBuildCommandService.class);
        blockBuildCommandService.initializeBuilds();

        // restore any outstanding block backups
        // BlockBackupService blockBackupService = injector.getInstance(BlockBackupService.class);
        // TODO blockBackupService.restoreAll();
    }

    @Override
    public void onDisable() {
        ServerShutdownEvent shutdownEvent = new ServerShutdownEvent();
        eventService.publish(shutdownEvent);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO initialize command handlers here
        return true;
    }
}
