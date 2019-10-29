package gg.warcraft.monolith.api.command.service;

import java.util.List;
import java.util.UUID;

/**
 * This adapter is injectable, however you generally have no need for it. Use the command and query services instead.
 * <p>
 * The CommandServerAdapter abstracts command related server implementations from the Monolith domain. It can be used to
 * query whether a command is currently in use on the server, to directly inject a new command into the command map of
 * the server, and to dispatch a command as if a player or the console executed it.
 */
public interface CommandServerAdapter {

    /**
     * @param alias The alias. Can not be null or empty.
     * @return True if the alias is not currently in use by another command, false otherwise.
     */
    boolean isAliasAvailable(String alias);

    /**
     * @param command  The command and all arguments space separated to execute. Can not be null or empty.
     * @param playerId The id of the player to execute the command as. Can not be null.
     */
    void dispatchCommandFor(String command, UUID playerId);

    /**
     * @param command The command and all arguments space separated to execute as the console. Can not be null or empty.
     */
    void dispatchConsoleCommand(String command);

    /**
     * @param name    The name of the new command. Can not be null or empty.
     * @param aliases An optional list of aliases. Can not be null, but can be empty. Items can not be null or empty.
     */
    void registerCommand(String name, List<String> aliases) throws IllegalArgumentException;
}
