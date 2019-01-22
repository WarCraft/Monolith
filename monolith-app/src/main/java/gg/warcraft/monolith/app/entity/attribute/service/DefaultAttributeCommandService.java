package gg.warcraft.monolith.app.entity.attribute.service;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.entity.attribute.Attribute;
import gg.warcraft.monolith.api.entity.attribute.AttributeModifier;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.attribute.GenericAttribute;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeCommandService;
import gg.warcraft.monolith.api.entity.attribute.service.AttributeRepository;
import gg.warcraft.monolith.api.entity.service.EntityServerAdapter;
import gg.warcraft.monolith.app.entity.attribute.LazyAttributes;
import gg.warcraft.monolith.app.entity.attribute.SimpleAttributeModifier;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DefaultAttributeCommandService implements AttributeCommandService {
    private final AttributeRepository repository;
    private final EntityServerAdapter entityServerAdapter;

    @Inject
    public DefaultAttributeCommandService(AttributeRepository repository, EntityServerAdapter entityServerAdapter) {
        this.repository = repository;
        this.entityServerAdapter = entityServerAdapter;
    }

    @Override
    public AttributeModifier addAttributeModifier(UUID entityId, Attribute attribute, float value) {
        Attributes attributes = repository.getAttributes(entityId);
        Map<Attribute, Set<AttributeModifier>> newModifiers = attributes.getModifiers();

        Set<AttributeModifier> newAttributeModifiers = attributes.getModifiers(attribute);
        AttributeModifier modifier = new SimpleAttributeModifier(attribute, value);
        newAttributeModifiers.add(modifier);

        newModifiers.put(attribute, newAttributeModifiers);
        Attributes newAttributes = new LazyAttributes(this, entityServerAdapter, entityId, newModifiers);
        repository.save(newAttributes);
        modifier.addObserver(newAttributes);

        if (attribute instanceof GenericAttribute) {
            updateGenericAttribute(entityId, (GenericAttribute) attribute);
        }

        return modifier;
    }

    @Override
    public void removeAttributeModifier(UUID entityId, AttributeModifier modifier) {
        Attributes attributes = repository.getAttributes(entityId);
        if (attributes == null) {
            return;
        }

        Map<Attribute, Set<AttributeModifier>> newModifiers = attributes.getModifiers();

        Attribute attribute = modifier.getAttribute();
        Set<AttributeModifier> newAttributeModifiers = attributes.getModifiers(attribute);
        if (newAttributeModifiers.contains(modifier)) {
            newAttributeModifiers.remove(modifier);
            modifier.removeObserver(attributes);

            newModifiers.put(attribute, newAttributeModifiers);
            Attributes newAttributes = new LazyAttributes(this, entityServerAdapter, entityId, newModifiers);
            repository.save(newAttributes);

            if (attribute instanceof GenericAttribute) {
                updateGenericAttribute(entityId, (GenericAttribute) attribute);
            }
        }
    }

    @Override
    public void updateGenericAttribute(UUID entityId, GenericAttribute attribute) {
        entityServerAdapter.updateGenericAttribute(entityId, attribute);
    }
}
