package gg.warcraft.monolith.app;

import com.google.inject.Key;
import com.google.inject.PrivateModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
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
import gg.warcraft.monolith.api.entity.team.service.TeamCommandService;
import gg.warcraft.monolith.api.entity.team.service.TeamQueryService;
import gg.warcraft.monolith.api.entity.team.service.TeamRepository;
import gg.warcraft.monolith.api.item.ItemReader;
import gg.warcraft.monolith.api.item.ItemReaderFactory;
import gg.warcraft.monolith.api.math.Vector3i;
import gg.warcraft.monolith.api.util.ColorCodeUtils;
import gg.warcraft.monolith.api.util.MathUtils;
import gg.warcraft.monolith.api.util.StringUtils;
import gg.warcraft.monolith.api.util.TimeUtils;
import gg.warcraft.monolith.api.world.World;
import gg.warcraft.monolith.api.block.BlockIterator;
import gg.warcraft.monolith.api.block.BlockIteratorFactory;
import gg.warcraft.monolith.api.block.BlockUtils;
import gg.warcraft.monolith.api.block.build.service.BlockBuildCommandService;
import gg.warcraft.monolith.api.block.build.service.BlockBuildQueryService;
import gg.warcraft.monolith.api.block.build.service.BlockBuildRepository;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingCommandService;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingQueryService;
import gg.warcraft.monolith.api.block.spoofing.BlockSpoofingRepository;
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
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamCommandService;
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamQueryService;
import gg.warcraft.monolith.app.entity.team.service.DefaultTeamRepository;
import gg.warcraft.monolith.app.item.SimpleItemReader;
import gg.warcraft.monolith.app.util.DefaultColorCodeUtils;
import gg.warcraft.monolith.app.util.DefaultMathUtils;
import gg.warcraft.monolith.app.util.DefaultStringUtils;
import gg.warcraft.monolith.app.util.DefaultTimeUtils;
import gg.warcraft.monolith.app.world.block.DefaultBlockUtils;
import gg.warcraft.monolith.app.world.block.SimpleBlockIterator;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildCommandService;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildQueryService;
import gg.warcraft.monolith.app.world.block.build.service.DefaultBlockBuildRepository;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingCommandService;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingQueryService;
import gg.warcraft.monolith.app.world.block.spoofing.DefaultBlockSpoofingRepository;

public class AbstractMonolithModule extends PrivateModule {
    private final String persistenceService;
    private final String redisHost;
    private final int redisPort;
    private final String configurationService;
    private final String gitHubAccount;
    private final String gitHubRepository;
    private final float baseHealth;
    private final World buildRepositoryWorld;
    private final Vector3i buildRepositoryMinimumCorner;
    private final Vector3i buildRepositoryMaximumCorner;

    public AbstractMonolithModule(String configurationService, String gitHubAccount, String gitHubRepository,
                                  String persistenceService, String redisHost, int redisPort,
                                  float baseHealth, World buildRepositoryWorld,
                                  Vector3i buildRepositoryMinimumCorner, Vector3i buildRepositoryMaximumCorner) {
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
        configureEffect();
        configureEntity();
        configureItem();
        configureUtil();
        configureWorld();
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
        // TODO bind ItemStorageService

        install(new FactoryModuleBuilder()
                .implement(ItemReader.class, SimpleItemReader.class)
                .build(ItemReaderFactory.class));
        expose(ItemReaderFactory.class);
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
        // Block bindings
        bind(BlockUtils.class).to(DefaultBlockUtils.class);
        expose(BlockUtils.class);

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

        // Block spoofing bindings
        bind(BlockSpoofingCommandService.class).to(DefaultBlockSpoofingCommandService.class);
        expose(BlockSpoofingCommandService.class);

        bind(BlockSpoofingQueryService.class).to(DefaultBlockSpoofingQueryService.class);
        expose(BlockSpoofingQueryService.class);

        bind(BlockSpoofingRepository.class).to(DefaultBlockSpoofingRepository.class);
        expose(BlockSpoofingRepository.class);

        // Misc world bindings
        bind(World.class).annotatedWith(Names.named("BuildRepositoryWorld"))
                .toInstance(buildRepositoryWorld);
        bind(Vector3i.class).annotatedWith(Names.named("BuildRepositoryMinimumCorner"))
                .toInstance(buildRepositoryMinimumCorner);
        bind(Vector3i.class).annotatedWith(Names.named("BuildRepositoryMaximumCorner"))
                .toInstance(buildRepositoryMaximumCorner);
    }
}
