package action;

import static io.CommandBuilder.aPostCommand;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.Command;

import org.junit.Before;
import org.junit.Test;

public class ActionsTest {
    private static final String USERNAME = "Alice";
    private static final Command COMMAND = aPostCommand().withUser(USERNAME).create();

    private Action executableCommand = mock(Action.class);
    private Action notExecutableCommand = mock(Action.class);

    @Before
    public void setUpCommands() {
        when(executableCommand.isExecutable(COMMAND)).thenReturn(true);
        when(notExecutableCommand.isExecutable(COMMAND)).thenReturn(false);
    }

    @Test
    public void givenMultipleCommandsTheFirstExecutableIsExecuted() {
        Actions commands = new Actions(asList(notExecutableCommand, executableCommand));

        commands.execute(COMMAND);

        verify(executableCommand).execute(COMMAND);
        verify(notExecutableCommand, never()) //
            .execute(any(Command.class));
    }

}
