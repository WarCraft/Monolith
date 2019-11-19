package gg.warcraft.monolith.api.core;

import java.util.UUID;

/**
 * This service is injectable.
 * <p>
 * The AuthorizationService allows for easy separation of accessibility to functionality within the plugin depending on
 * the level of permissions interacting players have. The actual permission strings used by the implementation can be
 * defined in the plugin configuration file.
 */
public interface AuthorizationService {

    /**
     * Checks whether or not the player with the specified id is a developer.
     * <p>
     * The extra granularity provided by having developer as a separate permission allows you to restrict volatile
     * (perhaps incomplete) commands only to those that are actively working on them.
     *
     * @return True if the player with the specified id has the dev permission, false otherwise.
     */
    boolean isDev(UUID playerId);

    /**
     * Checks whether or not the player with the specified id is a member of staff.
     *
     * @return True if the player with the specified id has the staff permission, false otherwise.
     */
    boolean isStaff(UUID playerId);

    /**
     * Checks whether or not the player with the specified id is an admin.
     *
     * @return True if the player with the specified id has the admin permission, false otherwise.
     */
    boolean isAdmin(UUID playerId);

    /**
     * Checks whether or not the player with the specified id is a moderator.
     *
     * @return True if the player with the specified id has the moderator permission, false otherwise.
     */
    boolean isMod(UUID playerId);

    /**
     * Checks Whether or not the player with the specified id is an administrator or a moderator that is currently
     * moderating the server and needs access to special functionality.
     * <p>
     * Staff are considered to be moderating when holding a piece of flint in their offhand. Extra functionality could
     * include reading who placed a block when punching one while moderating.
     * <p>
     * TODO: allow customization of moderating item
     *
     * @return True if the player with the specified id is a member of staff and is currently holding flint in their
     * offhand, false otherwise.
     */
    boolean isModerating(UUID playerId);

    boolean isDebugging(UUID playerId);
}
