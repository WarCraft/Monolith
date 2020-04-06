package gg.warcraft.monolith.spigot.entity.attribute

import gg.warcraft.monolith.api.entity.attribute.AttributeService

class SpigotAttributeService extends AttributeService {

}

/*
   @Override
    public float getGenericAttribute(UUID entityId, GenericAttribute attribute) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Attribute spigotAttribute = genericAttributeMapper.map(attribute);
            AttributeInstance spigotAttributeInstance = livingEntity.getAttribute(spigotAttribute);
            return (float) spigotAttributeInstance.getValue();
        }
        return 0;
    }

    @Override
    public void updateGenericAttribute(UUID entityId, GenericAttribute attribute) {
        Entity entity = server.getEntity(entityId);
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            Attributes attributes = attributeQueryService.getAttributes(entityId);
            float monolithValue = attributes.getModifiers(attribute).stream()
                    .map(AttributeModifier::getValue)
                    .reduce(Float::sum)
                    .orElse(0f);
            Attribute spigotAttribute = genericAttributeMapper.map(attribute);
            AttributeInstance spigotAttributeInstance = livingEntity.getAttribute(spigotAttribute);
            double defaultValue = spigotAttributeInstance.getDefaultValue();
            spigotAttributeInstance.setBaseValue(defaultValue + monolithValue);
        }
    }
 */
