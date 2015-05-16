package command;

import static io.CommandParameterBuilder.aPostCommand;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.CommandParameter;

import org.junit.Before;
import org.junit.Test;

public class CommandsTest {
    private static final String USERNAME = "Alice";
    private static final CommandParameter COMMAND_PARAMETER = aPostCommand().withUser(USERNAME).create();

    private Command applicableCommand = mock(Command.class);
    private Command notApplicableCommand = mock(Command.class);

    @Before
    public void setUpCommands() {
        when(applicableCommand.isApplicable(COMMAND_PARAMETER)).thenReturn(true);
        when(notApplicableCommand.isApplicable(COMMAND_PARAMETER)).thenReturn(false);
    }

    @Test
    public void givenMultipleCommandsTheFirstApplicableIsExecuted() {
        Commands commands = new Commands(asList(notApplicableCommand, applicableCommand));

        commands.execute(COMMAND_PARAMETER);

        verify(applicableCommand).executeCommand(COMMAND_PARAMETER);
        verify(notApplicableCommand, never()) //
            .executeCommand(any(CommandParameter.class));
    }

}
