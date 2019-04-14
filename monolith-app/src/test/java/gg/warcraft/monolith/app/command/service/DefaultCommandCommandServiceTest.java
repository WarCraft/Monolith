package gg.warcraft.monolith.app.command.service;

import gg.warcraft.monolith.api.command.Command;
import gg.warcraft.monolith.api.command.CommandHandler;
import gg.warcraft.monolith.api.command.service.CommandQueryService;
import gg.warcraft.monolith.api.command.service.CommandRepository;
import gg.warcraft.monolith.api.command.service.CommandServerAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.function.Predicate;
import java.util.logging.Logger;

import static gg.warcraft.monolith.app.command.CommandTestUtils.randomArgument;
import static gg.warcraft.monolith.app.command.CommandTestUtils.randomName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCommandCommandServiceTest {
    private DefaultCommandCommandService defaultCommandCommandService;

    @Mock private CommandQueryService mockCommandQueryService;
    @Mock private CommandRepository mockCommandRepository;
    @Mock private CommandServerAdapter mockCommandServerAdapter;
    @Mock private Command mockCommand;
    @Mock private CommandHandler mockCommandHandler;
    @Mock private Logger mockLogger;

    @Before
    public void beforeEach() {
        defaultCommandCommandService = new DefaultCommandCommandService(mockCommandQueryService,
                mockCommandRepository, mockCommandServerAdapter, mockLogger);
    }

    @After
    public void afterEach() {
        reset(mockCommandQueryService, mockCommandRepository, mockCommandServerAdapter, mockCommand,
                mockCommandHandler);
    }

    @Test
    public void validateName_shouldReturnTrue() {
        // Given
        String name = randomName();
        Predicate<String> isAvailable = command -> true;

        // When
        boolean result = defaultCommandCommandService.validateName(name, isAvailable);

        // Then
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateName_shouldThrowOnNull() {
        // Given
        Predicate<String> isAvailable = command -> true;

        // When
        defaultCommandCommandService.validateName(null, isAvailable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateName_shouldThrowOnEmpty() {
        // Given
        String name = "";
        Predicate<String> isAvailable = command -> true;

        // When
        defaultCommandCommandService.validateName(name, isAvailable);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    // NOTE this functionality has been replaced by simply logging a warning
    public void validateName_shouldThrowOnUnavailable() {
        // Given
        String name = randomName();
        Predicate<String> isAvailable = command -> false;

        // When
        defaultCommandCommandService.validateName(name, isAvailable);
    }

    @Test
    public void validateAlias_shouldReturnTrue() {
        // Given
        String name = randomName();
        String alias = randomName();
        Predicate<String> isAvailable = command -> true;

        // When
        boolean result = defaultCommandCommandService.validateAlias(name, alias, isAvailable);

        // Then
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateAlias_shouldThrowOnNull() {
        // Given
        String name = randomName();
        Predicate<String> isAvailable = command -> true;

        // When
        defaultCommandCommandService.validateAlias(name, null, isAvailable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateAlias_shouldThrowOnEmpty() {
        // Given
        String name = randomName();
        String alias = "";
        Predicate<String> isAvailable = command -> true;

        // When
        defaultCommandCommandService.validateAlias(name, alias, isAvailable);
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    // NOTE this functionality has been replaced by simply logging a warning
    public void validateAlias_shouldThrowOnUnavailable() {
        // Given
        String name = randomName();
        String alias = randomName();
        Predicate<String> isAvailable = command -> false;

        // When
        defaultCommandCommandService.validateAlias(name, alias, isAvailable);
    }

    @Test
    public void validateHandler_shouldReturnTrue() {
        // When
        boolean result = defaultCommandCommandService.validateHandler(mockCommandHandler);

        // Then
        assertTrue(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateHandler_shouldThrowOnNull() {
        // When
        defaultCommandCommandService.validateHandler(null);
    }

    @Test
    public void formatCommand_shouldJoinArguments() {
        // Given
        String name = randomName();
        String argument1 = randomArgument();
        String argument2 = randomArgument();

        // When
        String formattedCommand = defaultCommandCommandService.formatCommand(name, argument1, argument2);

        // Then
        assertEquals(name + " " + argument1 + " " + argument2, formattedCommand);
    }
}
