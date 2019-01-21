package gg.warcraft.monolith.spigot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import gg.warcraft.monolith.api.Monolith;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.core.TaskService;
import gg.warcraft.monolith.api.core.event.ServerShutdownEvent;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.WorldType;
import gg.warcraft.monolith.api.world.block.backup.BlockBackup;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupCommandService;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupQueryService;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.app.command.ConsoleCommandSender;
import gg.warcraft.monolith.app.command.PlayerCommandSender;
import gg.warcraft.monolith.app.command.event.SimpleCommandExecutedEvent;
import gg.warcraft.monolith.app.command.handler.CommandExecutedHandler;
import gg.warcraft.monolith.app.core.event.SimpleServerShutdownEvent;
import gg.warcraft.monolith.app.core.handler.DailyTickHandler;
import gg.warcraft.monolith.app.entity.attribute.handler.AttributesInitializationHandler;
import gg.warcraft.monolith.app.entity.handler.EntityProfileInitializationHandler;
import gg.warcraft.monolith.app.entity.player.handler.PlayerProfileInitializationHandler;
import gg.warcraft.monolith.app.entity.player.handler.PlayerProfileUpdateHandler;
import gg.warcraft.monolith.app.entity.player.hiding.handler.PlayerHidingHandler;
import gg.warcraft.monolith.app.entity.status.handler.StatusEffectHandler;
import gg.warcraft.monolith.app.entity.team.handler.TeamInitializationHandler;
import gg.warcraft.monolith.app.world.portal.handler.PortalEntryTaskHandler;
import gg.warcraft.monolith.spigot.entity.handler.EntityRemovalHandler;
import gg.warcraft.monolith.spigot.event.SpigotEntityEventMapper;
import gg.warcraft.monolith.spigot.event.SpigotInventoryEventMapper;
import gg.warcraft.monolith.spigot.event.SpigotPlayerEventMapper;
import gg.warcraft.monolith.spigot.event.SpigotProjectileEventMapper;
import gg.warcraft.monolith.spigot.event.SpigotWorldEventMapper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Arrays;
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
        CommandExecutedHandler commandExecutedHandler = injector.getInstance(CommandExecutedHandler.class);
        eventService.subscribe(commandExecutedHandler);

        TeamInitializationHandler teamInitializationHandler = injector.getInstance(TeamInitializationHandler.class);
        eventService.subscribe(teamInitializationHandler);

        AttributesInitializationHandler attributesInitializationHandler =
                injector.getInstance(AttributesInitializationHandler.class);
        eventService.subscribe(attributesInitializationHandler);

        StatusEffectHandler statusEffectHandler = injector.getInstance(StatusEffectHandler.class);
        eventService.subscribe(statusEffectHandler);

        EntityProfileInitializationHandler entityProfileInitializationHandler =
                injector.getInstance(EntityProfileInitializationHandler.class);
        eventService.subscribe(entityProfileInitializationHandler);

        PlayerProfileInitializationHandler playerProfileInitializationHandler =
                injector.getInstance(PlayerProfileInitializationHandler.class);
        eventService.subscribe(playerProfileInitializationHandler);

        TimeUtils timeUtils = injector.getInstance(TimeUtils.class);
        EntityRemovalHandler entityRemovalHandler = injector.getInstance(EntityRemovalHandler.class);
        taskService.runTask(entityRemovalHandler, timeUtils.createDurationInSeconds(10), timeUtils.createDurationInSeconds(10));

        PlayerProfileUpdateHandler playerProfileUpdateHandler = injector.getInstance(PlayerProfileUpdateHandler.class);
        taskService.runTask(playerProfileUpdateHandler, timeUtils.oneTick(), timeUtils.createDurationInSeconds(1));

        PlayerHidingHandler playerHidingHandler = injector.getInstance(PlayerHidingHandler.class);
        eventService.subscribe(playerHidingHandler);

        PortalEntryTaskHandler portalEntryTaskHandler = injector.getInstance(PortalEntryTaskHandler.class);
        taskService.runTask(portalEntryTaskHandler, timeUtils.createDurationInMillis(250), timeUtils.createDurationInMillis(250));

        DailyTickHandler dailyTickHandler = injector.getInstance(DailyTickHandler.class);
        taskService.runTask(dailyTickHandler, timeUtils.createDurationInMillis(1900), timeUtils.createDurationInMillis(1900));
    }

    void initializeSpigotEventMappers() {
        PluginManager pluginManager = getServer().getPluginManager();

        SpigotEntityEventMapper entityEventMapper = injector.getInstance(SpigotEntityEventMapper.class);
        pluginManager.registerEvents(entityEventMapper, this);

        SpigotInventoryEventMapper inventoryEventMapper = injector.getInstance(SpigotInventoryEventMapper.class);
        pluginManager.registerEvents(inventoryEventMapper, this);

        SpigotPlayerEventMapper playerEventMapper = injector.getInstance(SpigotPlayerEventMapper.class);
        pluginManager.registerEvents(playerEventMapper, this);

        SpigotProjectileEventMapper projectileEventMapper = injector.getInstance(SpigotProjectileEventMapper.class);
        pluginManager.registerEvents(projectileEventMapper, this);

        SpigotWorldEventMapper worldEventMapper = injector.getInstance(SpigotWorldEventMapper.class);
        pluginManager.registerEvents(worldEventMapper, this);
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();
        FileConfiguration localConfig = getConfig();

        String configurationService = localConfig.getString("configurationService");
        String gitHubAccount = localConfig.getString("gitHubAccount");
        String gitHubRepository = localConfig.getString("gitHubRepository");

        String persistenceService = localConfig.getString("persistenceService");
        String redisHost = localConfig.getString("redisHost");
        int redisPort = localConfig.getInt("redisPort");

        float baseHealth = (float) localConfig.getDouble("baseHealth");

        String buildRepositoryWorldString = localConfig.getString("buildRepository.world");
        WorldType buildRepositoryWorld = WorldType.valueOf(buildRepositoryWorldString);
        Vector3ic buildRepositoryMinimumCorner = new Vector3i(
                localConfig.getInt("buildRepository.minimumCorner.x"),
                localConfig.getInt("buildRepository.minimumCorner.y"),
                localConfig.getInt("buildRepository.minimumCorner.z"));
        Vector3ic buildRepositoryMaximumCorner = new Vector3i(
                localConfig.getInt("buildRepository.maximumCorner.x"),
                localConfig.getInt("buildRepository.maximumCorner.y"),
                localConfig.getInt("buildRepository.maximumCorner.z"));

        String overworldName = localConfig.getString("worldDirectoryName");
        String netherName = localConfig.getString("netherDirectoryName");
        String theEndName = localConfig.getString("endDirectoryName");

        Module spigotMonolithModule = new SpigotMonolithModule(
                configurationService, gitHubAccount, gitHubRepository,
                persistenceService, redisHost, redisPort,
                baseHealth, buildRepositoryWorld,
                buildRepositoryMinimumCorner, buildRepositoryMaximumCorner,
                this, overworldName, netherName, theEndName);
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
        BlockBackupQueryService blockBackupQueryService = injector.getInstance(BlockBackupQueryService.class);
        BlockBackupCommandService blockBackupCommandService = injector.getInstance(BlockBackupCommandService.class);
        blockBackupQueryService.getAllBlockBackups().stream()
                .map(BlockBackup::getId)
                .forEach(blockBackupCommandService::restoreBlockBackup);
    }

    @Override
    public void onDisable() {
        ServerShutdownEvent shutdownEvent = new SimpleServerShutdownEvent();
        eventService.publish(shutdownEvent);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        eventService.publish(new SimpleCommandExecutedEvent(
                (sender instanceof Player)
                        ? new PlayerCommandSender(sender.getName(), ((Player) sender).getUniqueId())
                        : new ConsoleCommandSender(),
                command.getName(),
                label,
                Arrays.asList(args)));
        return true;
    }
}
