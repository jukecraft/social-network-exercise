package org.twitterconsole.action;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.twitterconsole.io.CommandBuilder.aPostCommand;

import org.junit.Test;
import org.twitterconsole.io.Command;

public class ActionsShould {
    private static final String USERNAME = "Alice";
    private static final Command COMMAND = aPostCommand().withUser(USERNAME).create();

    private Action anAction = mock(Action.class);
    private Action anotherAction = mock(Action.class);

    @Test
    public void executeEveryGivenActionWithTheGivenCommand() {
        Actions commands = new Actions(asList(anAction, anotherAction));

        commands.execute(COMMAND);

        verify(anAction).execute(COMMAND);
        verify(anotherAction).execute(COMMAND);
    }

}
