package application;

import static application.ApplicationFactory.standardConfiguration;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final CommandParameter EMPTY_COMMAND_PARAMETER = new CommandParameter("");
    private static final CommandParameter A_COMMAND_PARAMETER = new CommandParameter(
        "Alice -> I love the weather today");
    private static final CommandParameter ANOTHER_COMMAND_PARAMETER = new CommandParameter("Alice");

    @Rule
    public Timeout timeout = new Timeout(1, SECONDS);

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);;
    private Commands commands = mock(Commands.class);
    private SocialNetworkingApplication applicationWithConsole = new SocialNetworkingApplication(
        standardConfiguration() //
            .withCommands(commands) //
            .withConsole(console));

    @Test
    public void givenTheApplicationWasStartedAWelcomeMessageIsShown() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND_PARAMETER);

        applicationWithConsole.start();

        verify(console).print("Welcome to my social network application");
    }

    @Test
    public void givenAnInputItRoutesThatInputToTheSocialNetworkingApplication() {
        when(console.getNextCommand()) //
            .thenReturn(A_COMMAND_PARAMETER) //
            .thenReturn(EMPTY_COMMAND_PARAMETER);

        applicationWithConsole.start();

        verify(commands).execute(A_COMMAND_PARAMETER);
    }

    @Test
    public void givenAnEmptyCommandNothingHappensThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND_PARAMETER) //
            .thenReturn(A_COMMAND_PARAMETER);

        applicationWithConsole.start();

        verifyZeroInteractions(commands);
    }

    @Test
    public void givenAnCommandThatHasLengthZeroThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()) //
            .thenReturn(EMPTY_COMMAND_PARAMETER);

        applicationWithConsole.start();

        verifyZeroInteractions(commands);
    }

    @Test
    public void givenTwoInputsItRoutesBothToTheSocialNetworkingApplication() {
        when(console.getNextCommand()) //
            .thenReturn(A_COMMAND_PARAMETER)//
            .thenReturn(ANOTHER_COMMAND_PARAMETER) //
            .thenReturn(EMPTY_COMMAND_PARAMETER);

        applicationWithConsole.start();

        verify(commands).execute(A_COMMAND_PARAMETER);
        verify(commands).execute(ANOTHER_COMMAND_PARAMETER);
    }

}
