package gg.warcraft.monolith.app.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import gg.warcraft.monolith.api.config.BoundingBlockBoxConfiguration;
import gg.warcraft.monolith.api.config.PortalConfiguration;

public class MonolithMapperModule extends SimpleModule {

    public MonolithMapperModule() {
        super("MonolithMapperModule", Version.unknownVersion());
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(BoundingBlockBoxConfiguration.class, JacksonBoundingBlockBoxConfiguration.class);
        resolver.addMapping(PortalConfiguration.class, JacksonPortalConfiguration.class);
        setAbstractTypes(resolver);
    }
}
