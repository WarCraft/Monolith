package gg.warcraft.monolith.app.entity;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import gg.warcraft.monolith.api.entity.Entity;
import gg.warcraft.monolith.api.entity.EntityProfile;
import gg.warcraft.monolith.api.entity.EntityServerData;
import gg.warcraft.monolith.api.entity.EntityType;
import gg.warcraft.monolith.api.entity.Equipment;
import gg.warcraft.monolith.api.entity.MonolithEntityData;
import gg.warcraft.monolith.api.entity.attribute.Attributes;
import gg.warcraft.monolith.api.entity.status.Status;
import gg.warcraft.monolith.api.entity.team.Team;
import gg.warcraft.monolith.api.entity.team.service.TeamQueryService;
import gg.warcraft.monolith.api.util.Lazy;
import gg.warcraft.monolith.api.world.location.OrientedLocation;
import org.joml.AABBf;
import org.joml.Vector3f;

import java.util.UUID;
import java.util.function.Supplier;

public class LazyEntity implements Entity {
    private final TeamQueryService teamQueryService;
    private final Lazy<? extends EntityProfile> profile;
    private final Lazy<? extends EntityServerData> serverData;
    private final Lazy<Attributes> attributes;
    private final Lazy<Status> status;

    @Inject
    public LazyEntity(TeamQueryService teamQueryService,
                      @Assisted("profile") Supplier<? extends EntityProfile> profileSupplier,
                      @Assisted("data") Supplier<? extends EntityServerData> serverDataSupplier,
                      @Assisted("attributes") Supplier<Attributes> attributesSupplier,
                      @Assisted("status") Supplier<Status> statusSupplier) {
        this.teamQueryService = teamQueryService;
        this.profile = new Lazy<>(profileSupplier);
        this.serverData = new Lazy<>(serverDataSupplier);
        this.attributes = new Lazy<>(attributesSupplier);
        this.status = new Lazy<>(statusSupplier);
    }

    @Override
    public UUID getId() {
        return serverData.get().getEntityId();
    }

    @Override
    public EntityType getType() {
        return serverData.get().getType();
    }

    @Override
    public String getName() {
        return serverData.get().getName();
    }

    @Override
    public OrientedLocation getLocation() {
        return serverData.get().getLocation();
    }

    @Override
    public OrientedLocation getEyeLocation() {
        return serverData.get().getEyeLocation();
    }

    @Override
    public Vector3f getVelocity() {
        return serverData.get().getVelocity();
    }

    @Override
    public float getHealth() {
        return serverData.get().getHealth();
    }

    @Override
    public Team getTeam() {
        String team = getData(MonolithEntityData.TEAM.getKey());
        return teamQueryService.getTeam(team);
    }

    @Override
    public String getData(String key) {
        EntityProfile entityProfile = profile.get();
        if (entityProfile != null) {
            return entityProfile.getData().get(key);
        }
        return null;
    }

    @Override
    public int getDataInt(String key) {
        String data = getData(key);
        try {
            return Integer.parseInt(data);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public float getDataFoat(String key) {
        String data = getData(key);
        try {
            return Float.parseFloat(data);
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public boolean getDataBool(String key) {
        String data = getData(key);
        try {
            return Boolean.parseBoolean(data);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Attributes getAttributes() {
        return attributes.get();
    }

    @Override
    public Status getStatus() {
        return status.get();
    }

    @Override
    public Equipment getEquipment() {
        return serverData.get().getEquipment();
    }

    @Override
    public AABBf getBoundingBox() {
        return serverData.get().getBoundingBox();
    }

    @Override
    public boolean isGrounded() {
        return serverData.get().isGrounded();
    }

    @Override
    public boolean hasPermission(String permission) {
        return serverData.get().hasPermission(permission);
    }
}
