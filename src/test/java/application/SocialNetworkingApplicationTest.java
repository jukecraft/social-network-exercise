package application;

import static io.CommandBuilder.aFollowsCommand;
import static io.CommandBuilder.aPostCommand;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import io.Command;
import io.SocialNetworkingConsole;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import action.Actions;

public class SocialNetworkingApplicationTest {
    private static final Command EMPTY_COMMAND = new Command("");
    private static final Command A_COMMAND = aPostCommand().create();
    private static final Command ANOTHER_COMMAND = aFollowsCommand().create();

    @Rule
    public Timeout timeout = new Timeout(1, SECONDS);

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);
    private Actions actions = mock(Actions.class);
    private SocialNetworkingApplication applicationWithConsole = new SocialNetworkingApplication(actions, console);

    @Test
    public void givenAnCommandItExecutesThatCommandWithActions() {
        when(console.getNextCommand()) //
            .thenReturn(A_COMMAND) //
            .thenReturn(EMPTY_COMMAND);

        applicationWithConsole.start();

        verify(actions).execute(A_COMMAND);
    }

    @Test
    public void givenAnEmptyCommandNothingHappensThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND) //
            .thenReturn(A_COMMAND);

        applicationWithConsole.start();

        verifyZeroInteractions(actions);
    }

    @Test
    public void givenAnCommandThatHasLengthZeroThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND);

        applicationWithConsole.start();

        verifyZeroInteractions(actions);
    }

    @Test
    public void givenTwoInputsItRoutesBothToTheSocialNetworkingApplication() {
        when(console.getNextCommand()) //
            .thenReturn(A_COMMAND)//
            .thenReturn(ANOTHER_COMMAND) //
            .thenReturn(EMPTY_COMMAND);

        applicationWithConsole.start();

        verify(actions).execute(A_COMMAND);
        verify(actions).execute(ANOTHER_COMMAND);
    }

    @Test
    public void whenRequestingAUserInputItPrintsAPrompt() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND);

        applicationWithConsole.start();

        verify(console).printPrompt();
    }

}
