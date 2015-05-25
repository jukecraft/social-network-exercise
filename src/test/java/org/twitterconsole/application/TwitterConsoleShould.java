package org.twitterconsole.application;

import static java.util.Optional.of;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aFollowsCommand;
import static org.twitterconsole.io.CommandBuilder.aPostCommand;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.Timeout;
import org.twitterconsole.action.Actions;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;

public class TwitterConsoleShould {
    private static final Command EMPTY_COMMAND = new Command("");
    private static final Command A_COMMAND = aPostCommand().create();
    private static final Command ANOTHER_COMMAND = aFollowsCommand().create();

    @Rule
    public Timeout timeout = new Timeout(1, SECONDS);
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);
    private Actions actions = mock(Actions.class);
    private TwitterConsole applicationWithConsole = new TwitterConsole(actions, console);

    @Test
    public void executeUserCommands() {
        when(console.getNextCommand())
            .thenReturn(of(A_COMMAND))
            .thenReturn(of(EMPTY_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verify(actions).execute(A_COMMAND);
    }

    @Test
    public void notReactToEmptyCommands() {
        when(console.getNextCommand())
            .thenReturn(of(EMPTY_COMMAND))
            .thenReturn(of(A_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verifyZeroInteractions(actions);
    }

    @Test
    public void notReactToCommandsWithZeroCharacters() {
        when(console.getNextCommand())
            .thenReturn(of(EMPTY_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verifyZeroInteractions(actions);
    }

    @Test
    public void executeMultipleCommands() {
        when(console.getNextCommand())
            .thenReturn(of(A_COMMAND))
            .thenReturn(of(ANOTHER_COMMAND))
            .thenReturn(of(EMPTY_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verify(actions).execute(A_COMMAND);
        verify(actions).execute(ANOTHER_COMMAND);
    }

    @Test
    public void printAPrompt() {
        when(console.getNextCommand())
            .thenReturn(of(EMPTY_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verify(console).printPrompt();
    }

    @Test
    public void printAPromptWithEveryCommand() {
        when(console.getNextCommand())
            .thenReturn(of(A_COMMAND))
            .thenReturn(of(ANOTHER_COMMAND))
            .thenReturn(of(EMPTY_COMMAND));

        exit.expectSystemExit();
        applicationWithConsole.start();

        verify(console, times(3)).printPrompt();
    }
}
