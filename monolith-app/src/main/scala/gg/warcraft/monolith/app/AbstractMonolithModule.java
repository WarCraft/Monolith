package gg.warcraft.monolith.app;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.Key;
import com.google.inject.PrivateModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import gg.warcraft.monolith.api.MonolithPluginUtils;
import gg.warcraft.monolith.api.combat.CombatFactory;
import gg.warcraft.monolith.api.combat.CombatSource;
import gg.warcraft.monolith.api.combat.PotionEffect;
import gg.warcraft.monolith.api.combat.PotionEffectTypeUtils;
import gg.warcraft.monolith.api.combat.value.CombatValue;
import gg.warcraft.monolith.api.combat.value.CombatValueModifier;
import gg.warcraft.monolith.api.command.CommandSender;
import gg.warcraft.monolith.api.command.Console;
import gg.warcraft.monolith.api.command.service.CommandCommandService;
import gg.warcraft.monolith.api.command.service.CommandQueryService;
import gg.warcraft.monolith.api.command.service.CommandRepository;
import gg.warcraft.monolith.api.config.service.ConfigurationCommandService;
import gg.warcraft.monolith.api.config.service.ConfigurationQueryService;
import gg.warcraft.monolith.api.config.service.ConfigurationRepository;
import gg.warcraft.monolith.api.core.EventService;
import gg.warcraft.monolith.api.core.JsonMapper;
import gg.warcraft.monolith.api.core.PersistenceCache;
import gg.warcraft.monolith.api.core.PersistenceService;
import gg.warcraft.monolith.api.core.YamlMapper;
import gg.warcraft.monolith.api.effect.Effect;
import gg.warcraft.monolith.api.effect.EffectFactory;
import gg.warcraft.monolith.api.effect.EffectRenderer;
import gg.warcraft.monolith.api.effect.EffectRendererFactory;
import gg.warcraft.monolith.api.effect.EffectVectors;
import gg.warcraft.monolith.api.effect.EffectVectorsFactory;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityFactory;
import gg.warcraft.monolith.api.entity.EntityUtils;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeCommandService;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeQueryService;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeRepository;
import gg.warcraft.monolith.api.entity.player.Player;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingCommandService;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingQueryService;
import gg.warcraft.monolith.api.entity.player.hiding.PlayerHidingRepository;
import gg.warcraft.monolith.api.entity.player.service.PlayerCommandService;
import gg.warcraft.monolith.api.entity.player.service.PlayerProfileRepository;
import gg.warcraft.monolith.api.entity.player.service.PlayerQueryService;
import gg.warcraft.monolith.api.entity.service.EntityCommandService;
import gg.warcraft.monolith.api.entity.service.EntityProfileRepository;
import gg.warcraft.monolith.api.entity.service.EntityQueryService;
import gg.warcraft.monolith.api.entity.service.EntityRepository;
import gg.warcraft.monolith.api.entity.status.service.StatusCommandService;
import gg.warcraft.monolith.api.entity.status.service.StatusQueryService;
import gg.warcraft.monolith.api.entity.status.service.StatusRepository;
import gg.warcraft.monolith.api.entity.team.service.TeamCommandService;
import gg.warcraft.monolith.api.entity.team.service.TeamQueryService;
import gg.warcraft.monolith.api.entity.team.service.TeamRepository;
import gg.warcraft.monolith.api.item.ItemBuilder;
import gg.warcraft.monolith.api.item.ItemBuilderFactory;
import gg.warcraft.monolith.api.item.ItemReader;
import gg.warcraft.monolith.api.item.ItemReaderFactory;
import gg.warcraft.monolith.api.item.ItemTypeUtils;
import gg.warcraft.monolith.api.item.service.ItemStorageCommandService;
import gg.warcraft.monolith.api.item.service.ItemStorageQueryService;
import gg.warcraft.monolith.api.item.service.ItemStorageRepository;
import gg.warcraft.monolith.api.menu.ButtonBuilder;
import gg.warcraft.monolith.api.menu.ButtonBuilderFactory;
import gg.warcraft.monolith.api.menu.MenuBuilder;
import gg.warcraft.monolith.api.menu.MenuBuilderFactory;
import gg.warcraft.monolith.api.menu.service.MenuCommandService;
import gg.warcraft.monolith.api.menu.service.MenuQueryService;
import gg.warcraft.monolith.api.menu.service.MenuRepository;
import gg.warcraft.monolith.api.util.ColorCodeUtils;
import gg.warcraft.monolith.api.util.MathUtils;
import gg.warcraft.monolith.api.util.StringUtils;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.DirectionUtils;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.world.block.BlockIterator;
import gg.warcraft.monolith.api.world.block.BlockIteratorFactory;
import gg.warcraft.monolith.api.world.block.BlockTypeUtils;
import gg.warcraft.monolith.api.world.block.BlockUtils;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupQueryService;
import gg.warcraft.monolith.api.world.block.backup.service.BlockBackupRepository;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBox;
import gg.warcraft.monolith.api.world.block.box.BoundingBlockBoxFactory;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildQueryService;
import gg.warcraft.monolith.api.world.block.build.service.BlockBuildRepository;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingCommandService;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingQueryService;
import gg.warcraft.monolith.api.world.block.spoofing.BlockSpoofingRepository;
import gg.warcraft.monolith.api.world.location.LocationFactory;
import gg.warcraft.monolith.api.world.portal.service.PortalCommandService;
import gg.warcraft.monolith.api.world.portal.service.PortalQueryService;
import gg.warcraft.monolith.api.world.portal.service.PortalRepository;
import gg.warcraft.monolith.api.world.service.WorldCommandService;
import gg.warcraft.monolith.api.world.service.WorldQueryService;
import gg.warcraft.monolith.app.combat.AmbientPotionEffect;
import gg.warcraft.monolith.app.combat.DefaultPotionEffectTypeUtils;
import gg.warcraft.monolith.app.combat.SimpleCombatSource;
import gg.warcraft.monolith.app.combat.SimplePotionEffect;
import gg.warcraft.monolith.app.combat.VisiblePotionEffect;
import gg.warcraft.monolith.app.combat.value.LazyCombatValue;
import gg.warcraft.monolith.app.combat.value.SimpleCombatValueModifier;
import gg.warcraft.monolith.app.command.ConsoleCommandSender;
import gg.warcraft.monolith.app.command.service.DefaultCommandCommandService;
import gg.warcraft.monolith.app.command.service.DefaultCommandQueryService;
import gg.warcraft.monolith.app.command.service.DefaultCommandRepository;
import gg.warcraft.monolith.app.config.MonolithMapperModule;
import gg.warcraft.monolith.app.config.service.DefaultConfigurationQueryService;
import gg.warcraft.monolith.app.config.service.DefaultConfigurationRepository;
import gg.warcraft.monolith.app.config.service.GitHubConfigurationCommandService;
import gg.warcraft.monolith.app.config.service.LocalConfigurationCommandService;
import gg.warcraft.monolith.app.core.GuavaEventService;
import gg.warcraft.monolith.app.core.InMemoryPersistenceCache;
import gg.warcraft.monolith.app.core.JedisPersistenceService;
import gg.warcraft.monolith.app.effect.DynamicEffect;
import gg.warcraft.monolith.app.effect.PeriodicDynamicEffect;
import gg.warcraft.monolith.app.effect.PeriodicEffect;
import gg.warcraft.monolith.app.effect.SimpleEffect;
import gg.warcraft.monolith.app.effect.renderer.IterativeEffectRenderer;
import gg.warcraft.monolith.app.effect.renderer.SimpleEffectRenderer;
import gg.warcraft.monolith.app.effect.vectors.CircleVectors;
import gg.warcraft.monolith.app.effect.vectors.DomeVectors;
import gg.warcraft.monolith.app.effect.vectors.LineVectors;
import gg.warcraft.monolith.app.effect.vectors.OriginVector;
import gg.warcraft.monolith.app.effect.vectors.PointVector;
import gg.warcraft.monolith.app.effect.vectors.RingVectors;
import gg.warcraft.monolith.app.effect.vectors.SphereVectors;
import gg.warcraft.monolith.app.entity.DefaultEntityUtils;
import gg.warcraft.monolith.app.entity.LazyEntity;
import gg.warcraft.monolith.app.entity.attribute.service.DefaultAttributeCommandService;
import gg.warcraft.monolith.app.entity.attribute.service.DefaultAttributeQueryService;
import gg.warcraft.monolith.app.entity.attribute.service.DefaultAttributeRepository;
import gg.warcraft.monolith.app.entity.player.LazyPlayer;
import gg.warcraft.monolith.app.entity.player.hiding.DefaultPlayerHidingCommandService;
import gg.warcraft.monolith.app.entity.player.hiding.DefaultPlayerHidingQueryService;
import gg.warcraft.monolith.app.entity.player.hiding.DefaultPlayerHidingRepository;
import gg.warcraft.monolith.app.entity.player.service.DefaultPlayerCommandService;
import gg.warcraft.monolith.app.entity.player.service.DefaultPlayerProfileRepository;
import gg.warcraft.monolith.app.entity.player.service.DefaultPlayerQueryService;
import gg.warcraft.monolith.app.entity.service.DefaultEntityCommandService;
import gg.warcraft.monolith.app.entity.service.DefaultEntityProfileRepository;
import gg.warcraft.monolith.app.entity.service.DefaultEntityQueryService;
import gg.warcraft.monolith.app.entity.service.DefaultEntityRepository;
import gg.warcraft.monolith.app.entity.status.service.DefaultStatusCommandService;
import gg.warcraft.monolith.app.entity.status.service.DefaultStatusQueryService;
import gg.warcraft.monolith.app.entity.status.service.DefaultStatusRepository;
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamCommandService;
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamQueryService;
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamRepository;
import gg.warcraft.monolith.app.item.DefaultItemTypeUtils;
import gg.warcraft.monolith.app.item.SimpleItemBuilder;
import gg.warcraft.monolith.app.item.SimpleItemReader;
import gg.warcraft.monolith.app.item.service.DefaultItemStorageCommandService;
import gg.warcraft.monolith.app.item.service.DefaultItemStorageQueryService;
import gg.warcraft.monolith.app.item.service.DefaultItemStorageRepository;
import gg.warcraft.monolith.app.menu.SimpleButtonBuilder;
import gg.warcraft.monolith.app.menu.SimpleMenuBuilder;
import gg.warcraft.monolith.app.menu.SkullButtonBuilder;
import gg.warcraft.monolith.app.menu.service.DefaultMenuCommandService;
import gg.warcraft.monolith.app.menu.service.DefaultMenuQueryService;
import gg.warcraft.monolith.app.menu.service.DefaultMenuRepository;
import gg.warcraft.monolith.app.util.DefaultColorCodeUtils;
import gg.warcraft.monolith.app.util.DefaultMathUtils;
import gg.warcraft.monolith.app.util.DefaultStringUtils;
import gg.warcraft.monolith.app.util.DefaultTimeUtils;
import gg.warcraft.monolith.app.world.DefaultDirectionUtils;
import gg.warcraft.monolith.app.world.block.DefaultBlockTypeUtils;
import gg.warcraft.monolith.app.world.block.DefaultBlockUtils;
import gg.warcraft.monolith.app.world.block.SimpleBlockIterator;
import gg.warcraft.monolith.app.world.block.backup.service.DefaultBlockBackupQueryService;
import gg.warcraft.monolith.app.world.block.backup.service.DefaultBlockBackupRepository;
import gg.warcraft.monolith.app.world.block.box.SimpleBoundingBlockBox;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildCommandService;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildQueryService;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildRepository;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingCommandService;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingQueryService;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingRepository;
import gg.warcraft.monolith.app.world.location.DefaultLocationFactory;
import gg.warcraft.monolith.app.world.portal.service.DefaultPortalCommandService;
import gg.warcraft.monolith.app.world.portal.service.DefaultPortalQueryService;
import gg.warcraft.monolith.app.world.portal.service.DefaultPortalRepository;
import gg.warcraft.monolith.app.world.service.DefaultWorldCommandService;
import gg.warcraft.monolith.app.world.service.DefaultWorldQueryService;
import org.joml.Vector3ic;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class AbstractMonolithModule extends PrivateModule {
    private final String persistenceService;
    private final String redisHost;
    private final int redisPort;
    private final String configurationService;
    private final String gitHubAccount;
    private final String gitHubRepository;
    private final float baseHealth;
    private final World buildRepositoryWorld;
    private final Vector3ic buildRepositoryMinimumCorner;
    private final Vector3ic buildRepositoryMaximumCorner;

    public AbstractMonolithModule(String configurationService, String gitHubAccount, String gitHubRepository,
                                  String persistenceService, String redisHost, int redisPort,
                                  float baseHealth, World buildRepositoryWorld,
                                  Vector3ic buildRepositoryMinimumCorner, Vector3ic buildRepositoryMaximumCorner) {
        this.configurationService = configurationService;
        this.gitHubAccount = gitHubAccount;
        this.gitHubRepository = gitHubRepository;
        this.persistenceService = persistenceService;
        this.redisHost = redisHost;
        this.redisPort = redisPort;
        this.baseHealth = baseHealth;
        this.buildRepositoryWorld = buildRepositoryWorld;
        this.buildRepositoryMinimumCorner = buildRepositoryMinimumCorner;
        this.buildRepositoryMaximumCorner = buildRepositoryMaximumCorner;
    }

    @Override
    protected void configure() {
        configureCombat();
        configureCommand();
        configureConfiguration();
        configureCore();
        configureEffect();
        configureEntity();
        configureItem();
        configureMenu();
        configurePersistence();
        configureUtil();
        configureWorld();
    }

    private void configureCombat() {
        bind(PotionEffectTypeUtils.class).to(DefaultPotionEffectTypeUtils.class);
        expose(PotionEffectTypeUtils.class);

        install(new FactoryModuleBuilder()
                .implement(PotionEffect.class, Names.named("potionEffect"), SimplePotionEffect.class)
                .implement(PotionEffect.class, Names.named("visiblePotionEffect"), VisiblePotionEffect.class)
                .implement(PotionEffect.class, Names.named("ambientPotionEffect"), AmbientPotionEffect.class)
                .implement(CombatSource.class, Names.named("source"), SimpleCombatSource.class)
                .implement(CombatValue.class, Names.named("value"), LazyCombatValue.class)
                .implement(CombatValueModifier.class, Names.named("modifier"), SimpleCombatValueModifier.class)
                .build(CombatFactory.class));
        expose(CombatFactory.class);
    }

    private void configureCommand() {
        bind(CommandCommandService.class).to(DefaultCommandCommandService.class);
        expose(CommandCommandService.class);

        bind(CommandQueryService.class).to(DefaultCommandQueryService.class);
        expose(CommandQueryService.class);

        bind(CommandRepository.class).to(DefaultCommandRepository.class);
        expose(CommandRepository.class);

        bind(CommandSender.class).annotatedWith(Console.class).to(ConsoleCommandSender.class);
        expose(Key.get(CommandSender.class, Console.class));
    }

    private void configureConfiguration() {
        switch (configurationService) {
            case "LOCAL":
                bind(ConfigurationCommandService.class).to(LocalConfigurationCommandService.class);
                expose(ConfigurationCommandService.class);

                bind(ConfigurationQueryService.class).to(DefaultConfigurationQueryService.class);
                expose(ConfigurationQueryService.class);

                bind(ConfigurationRepository.class).to(DefaultConfigurationRepository.class);
                expose(ConfigurationRepository.class);
                break;
            case "GITHUB":
                bind(String.class).annotatedWith(Names.named("GitHubAccount")).toInstance(gitHubAccount);
                bind(String.class).annotatedWith(Names.named("GitHubRepository")).toInstance(gitHubRepository);

                bind(ConfigurationCommandService.class).to(GitHubConfigurationCommandService.class);
                expose(ConfigurationCommandService.class);

                bind(ConfigurationQueryService.class).to(DefaultConfigurationQueryService.class);
                expose(ConfigurationQueryService.class);

                bind(ConfigurationRepository.class).to(DefaultConfigurationRepository.class);
                expose(ConfigurationRepository.class);
                break;
            case "CUSTOM":
                // do nothing, the implementing server should provide bindings
                break;
            default:
                throw new IllegalArgumentException("Illegal configuration service in Monolith configuration: " + configurationService);
        }
    }

    private void configureCore() {
        bind(MonolithPluginUtils.class).to(DefaultMonolithPluginUtils.class);
        expose(MonolithPluginUtils.class);

        bind(EventService.class).to(GuavaEventService.class);
        expose(EventService.class);

        SimpleModule monolithMapperModule = new MonolithMapperModule();

        ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonMapper.registerModule(monolithMapperModule);
        bind(ObjectMapper.class).annotatedWith(JsonMapper.class).toProvider(jsonMapper::copy);
        expose(Key.get(ObjectMapper.class, JsonMapper.class));

        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        yamlMapper.registerModule(monolithMapperModule);
        bind(ObjectMapper.class).annotatedWith(YamlMapper.class).toProvider(yamlMapper::copy);
        expose(Key.get(ObjectMapper.class, YamlMapper.class));
    }

    private void configureEffect() {
        install(new FactoryModuleBuilder()
                .implement(EffectVectors.class, Names.named("circle"), CircleVectors.class)
                .implement(EffectVectors.class, Names.named("dome"), DomeVectors.class)
                .implement(EffectVectors.class, Names.named("line"), LineVectors.class)
                .implement(EffectVectors.class, Names.named("origin"), OriginVector.class)
                .implement(EffectVectors.class, Names.named("point"), PointVector.class)
                .implement(EffectVectors.class, Names.named("ring"), RingVectors.class)
                .implement(EffectVectors.class, Names.named("sphere"), SphereVectors.class)
                .build(EffectVectorsFactory.class));
        expose(EffectVectorsFactory.class);

        install(new FactoryModuleBuilder()
                .implement(EffectRenderer.class, Names.named("simple"), SimpleEffectRenderer.class)
                .implement(EffectRenderer.class, Names.named("iterative"), IterativeEffectRenderer.class)
                .build(EffectRendererFactory.class));
        expose(EffectRendererFactory.class);

        install(new FactoryModuleBuilder()
                .implement(Effect.class, Names.named("simple"), SimpleEffect.class)
                .implement(Effect.class, Names.named("periodic"), PeriodicEffect.class)
                .implement(Effect.class, Names.named("dynamic"), DynamicEffect.class)
                .implement(Effect.class, Names.named("periodynamic"), PeriodicDynamicEffect.class)
                .build(EffectFactory.class));
        expose(EffectFactory.class);
    }

    private void configureEntity() {
        // Entity bindings
        bind(EntityCommandService.class).to(DefaultEntityCommandService.class);
        expose(EntityCommandService.class);

        bind(EntityQueryService.class).to(DefaultEntityQueryService.class);
        expose(EntityQueryService.class);

        bind(EntityRepository.class).to(DefaultEntityRepository.class);
        expose(EntityRepository.class);

        bind(EntityProfileRepository.class).to(DefaultEntityProfileRepository.class);
        expose(EntityProfileRepository.class);

        bind(EntityUtils.class).to(DefaultEntityUtils.class);
        expose(EntityUtils.class);

        // Player bindings
        bind(PlayerCommandService.class).to(DefaultPlayerCommandService.class);
        expose(PlayerCommandService.class);

        bind(PlayerQueryService.class).to(DefaultPlayerQueryService.class);
        expose(PlayerQueryService.class);

        bind(PlayerProfileRepository.class).to(DefaultPlayerProfileRepository.class);
        expose(PlayerProfileRepository.class);

        // Attribute bindings
        bind(AttributeCommandService.class).to(DefaultAttributeCommandService.class);
        expose(AttributeCommandService.class);

        bind(AttributeQueryService.class).to(DefaultAttributeQueryService.class);
        expose(AttributeQueryService.class);

        bind(AttributeRepository.class).to(DefaultAttributeRepository.class);
        expose(AttributeRepository.class);

        // Status bindings
        bind(StatusCommandService.class).to(DefaultStatusCommandService.class);
        expose(StatusCommandService.class);

        bind(StatusQueryService.class).to(DefaultStatusQueryService.class);
        expose(StatusQueryService.class);

        bind(StatusRepository.class).to(DefaultStatusRepository.class);
        expose(StatusRepository.class);

        // Team bindings
        bind(TeamCommandService.class).to(DefaultTeamCommandService.class);
        expose(TeamCommandService.class);

        bind(TeamQueryService.class).to(DefaultTeamQueryService.class);
        expose(TeamQueryService.class);

        bind(TeamRepository.class).to(DefaultTeamRepository.class);
        expose(TeamRepository.class);

        // Player hiding bindings
        bind(PlayerHidingCommandService.class).to(DefaultPlayerHidingCommandService.class);
        expose(PlayerHidingCommandService.class);

        bind(PlayerHidingQueryService.class).to(DefaultPlayerHidingQueryService.class);
        expose(PlayerHidingQueryService.class);

        bind(PlayerHidingRepository.class).to(DefaultPlayerHidingRepository.class);
        expose(PlayerHidingRepository.class);

        // Misc entity bindings
        install(new FactoryModuleBuilder()
                .implement(Entity.class, Names.named("entity"), LazyEntity.class)
                .implement(Player.class, Names.named("player"), LazyPlayer.class)
                .build(EntityFactory.class));
        expose(EntityFactory.class);

        bind(Float.class).annotatedWith(Names.named("BaseHealth")).toInstance(baseHealth);
        // TODO moving all configuration code out of the plugin class into a configuration class allows for the
        // TODO removal of this expose
        expose(Key.get(Float.class, Names.named("BaseHealth")));
    }

    private void configureItem() {
        bind(ItemTypeUtils.class).to(DefaultItemTypeUtils.class);
        expose(ItemTypeUtils.class);

        bind(ItemStorageCommandService.class).to(DefaultItemStorageCommandService.class);
        expose(ItemStorageCommandService.class);

        bind(ItemStorageQueryService.class).to(DefaultItemStorageQueryService.class);
        expose(ItemStorageQueryService.class);

        bind(ItemStorageRepository.class).to(DefaultItemStorageRepository.class);
        expose(ItemStorageRepository.class);

        install(new FactoryModuleBuilder()
                .implement(ItemBuilder.class, SimpleItemBuilder.class)
                .build(ItemBuilderFactory.class));
        expose(ItemBuilderFactory.class);

        install(new FactoryModuleBuilder()
                .implement(ItemReader.class, SimpleItemReader.class)
                .build(ItemReaderFactory.class));
        expose(ItemReaderFactory.class);
    }

    private void configureMenu() {
        bind(MenuCommandService.class).to(DefaultMenuCommandService.class);
        expose(MenuCommandService.class);

        bind(MenuQueryService.class).to(DefaultMenuQueryService.class);
        expose(MenuQueryService.class);

        bind(MenuRepository.class).to(DefaultMenuRepository.class);
        expose(MenuRepository.class);

        install(new FactoryModuleBuilder()
                .implement(ButtonBuilder.class, Names.named("simple"), SimpleButtonBuilder.class)
                .implement(ButtonBuilder.class, Names.named("skull"), SkullButtonBuilder.class)
                .build(ButtonBuilderFactory.class));
        expose(ButtonBuilderFactory.class);

        install(new FactoryModuleBuilder()
                .implement(MenuBuilder.class, SimpleMenuBuilder.class)
                .build(MenuBuilderFactory.class));
        expose(MenuBuilderFactory.class);
    }

    private void configurePersistence() {
        bind(PersistenceCache.class).to(InMemoryPersistenceCache.class);
        expose(PersistenceCache.class);

        switch (persistenceService) {
            case "REDIS":
                JedisPoolConfig jedisConfiguration = new JedisPoolConfig();
                jedisConfiguration.setBlockWhenExhausted(false);
                JedisPool jedisPool = new JedisPool(jedisConfiguration, redisHost, redisPort, 10);

                bind(JedisPool.class).toInstance(jedisPool);
                bind(PersistenceService.class).to(JedisPersistenceService.class);
                expose(PersistenceService.class);
                break;
            case "CUSTOM":
                // do nothing, the implementing server should provide bindings
                break;
            default:
                throw new IllegalArgumentException("Illegal persistence service in Monolith configuration: " + persistenceService);
        }
    }

    private void configureUtil() {
        bind(MathUtils.class).to(DefaultMathUtils.class);
        expose(MathUtils.class);

        bind(StringUtils.class).to(DefaultStringUtils.class);
        expose(StringUtils.class);

        bind(TimeUtils.class).to(DefaultTimeUtils.class);
        expose(TimeUtils.class);

        bind(ColorCodeUtils.class).to(DefaultColorCodeUtils.class);
        expose(ColorCodeUtils.class);
    }

    private void configureWorld() {
        // World bindings
        bind(WorldQueryService.class).to(DefaultWorldQueryService.class);
        expose(WorldQueryService.class);

        bind(WorldCommandService.class).to(DefaultWorldCommandService.class);
        expose(WorldCommandService.class);

        bind(DirectionUtils.class).to(DefaultDirectionUtils.class);
        expose(DirectionUtils.class);

        bind(LocationFactory.class).to(DefaultLocationFactory.class);
        expose(LocationFactory.class);

        // Block bindings
        bind(BlockUtils.class).to(DefaultBlockUtils.class);
        expose(BlockUtils.class);

        bind(BlockTypeUtils.class).to(DefaultBlockTypeUtils.class);
        expose(BlockTypeUtils.class);

        install(new FactoryModuleBuilder()
                .implement(BoundingBlockBox.class, SimpleBoundingBlockBox.class)
                .build(BoundingBlockBoxFactory.class));
        expose(BoundingBlockBoxFactory.class);

        install(new FactoryModuleBuilder()
                .implement(BlockIterator.class, SimpleBlockIterator.class)
                .build(BlockIteratorFactory.class));
        expose(BlockIteratorFactory.class);

        // Block build bindings
        bind(BlockBuildCommandService.class).to(DefaultBlockBuildCommandService.class);
        expose(BlockBuildCommandService.class);

        bind(BlockBuildQueryService.class).to(DefaultBlockBuildQueryService.class);
        expose(BlockBuildQueryService.class);

        bind(BlockBuildRepository.class).to(DefaultBlockBuildRepository.class);
        expose(BlockBuildRepository.class);

        // Block backup bindings, the command service is bound in the Spigot layer
        bind(BlockBackupQueryService.class).to(DefaultBlockBackupQueryService.class);
        expose(BlockBackupQueryService.class);

        bind(BlockBackupRepository.class).to(DefaultBlockBackupRepository.class);
        expose(BlockBackupRepository.class);

        // Block spoofing bindings
        bind(BlockSpoofingCommandService.class).to(DefaultBlockSpoofingCommandService.class);
        expose(BlockSpoofingCommandService.class);

        bind(BlockSpoofingQueryService.class).to(DefaultBlockSpoofingQueryService.class);
        expose(BlockSpoofingQueryService.class);

        bind(BlockSpoofingRepository.class).to(DefaultBlockSpoofingRepository.class);
        expose(BlockSpoofingRepository.class);

        // Portal bindings
        bind(PortalCommandService.class).to(DefaultPortalCommandService.class);
        expose(PortalCommandService.class);

        bind(PortalQueryService.class).to(DefaultPortalQueryService.class);
        expose(PortalQueryService.class);

        bind(PortalRepository.class).to(DefaultPortalRepository.class);
        expose(PortalRepository.class);

        // Misc world bindings
        bind(World.class).annotatedWith(Names.named("BuildRepositoryWorld"))
                .toInstance(buildRepositoryWorld);
        bind(Vector3ic.class).annotatedWith(Names.named("BuildRepositoryMinimumCorner"))
                .toInstance(buildRepositoryMinimumCorner);
        bind(Vector3ic.class).annotatedWith(Names.named("BuildRepositoryMaximumCorner"))
                .toInstance(buildRepositoryMaximumCorner);
    }
}
