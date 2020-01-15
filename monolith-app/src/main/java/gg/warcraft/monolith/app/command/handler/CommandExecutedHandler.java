package gg.warcraft.monolith.app.command.handler;

import com.google.inject.Inject;
import gg.warcraft.monolith.api.command.Command;
import gg.warcraft.monolith.api.command.event.CommandExecutedEvent;
import gg.warcraft.monolith.api.command.service.CommandQueryService;
import gg.warcraft.monolith.api.core.Event;
import gg.warcraft.monolith.api.core.EventHandler;

public class CommandExecutedHandler implements EventHandler {
    private final CommandQueryService queryService;

    @Inject
    public CommandExecutedHandler(CommandQueryService queryService) {
        this.queryService = queryService;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof CommandExecutedEvent) {
            onCommandExecuted((CommandExecutedEvent) event);
        }
    }

    public void onCommandExecuted(CommandExecutedEvent event) {
        Command command = queryService.getCommand(event.getCommand());
        if (command != null) {
            command.getHandler().onCommand(event.getSender(), command, event.getLabel(), event.getArguments());
        }
    }
}
