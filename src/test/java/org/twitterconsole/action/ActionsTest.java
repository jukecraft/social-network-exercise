package org.twitterconsole.action;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.twitterconsole.io.CommandBuilder.aPostCommand;

import org.junit.Test;
import org.twitterconsole.io.Command;

public class ActionsTest {
    private static final String USERNAME = "Alice";
    private static final Command COMMAND = aPostCommand().withUser(USERNAME).create();

    private Action executableCommand = mock(Action.class);
    private Action anotherCommand = mock(Action.class);

    @Test
    public void givenMultipleCommandsTheFirstExecutableIsExecuted() {
        Actions commands = new Actions(asList(executableCommand, anotherCommand));

        commands.execute(COMMAND);

        verify(executableCommand).execute(COMMAND);
        verify(anotherCommand).execute(COMMAND);
    }

}
