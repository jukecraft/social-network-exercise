package application;

import static application.ApplicationFactory.standardConfiguration;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class SocialNetworkingApplicationWithConsoleTest {
    private static final String A_COMMAND = "Alice -> I love the weather today";

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);;
    private SocialNetworkingApplication application = mock(SocialNetworkingApplication.class);
    private SocialNetworkingApplicationWithConsole applicationWithConsole = new SocialNetworkingApplicationWithConsole(
        standardConfiguration() //
            .withApplication(application) //
            .withConsole(console));

    @Test
    public void givenTheApplicationWasStartedAWelcomeMessageIsShown() {
        when(console.getNextCommand()).thenReturn(empty());

        applicationWithConsole.start();

        verify(console).print("Welcome to my social network application");
    }

    @Test
    public void givenAnInputItRoutesThatInputToTheSocialNetworkingApplication() {
        when(console.getNextCommand()).thenReturn(of(A_COMMAND));

        applicationWithConsole.start();

        verify(application).accept(A_COMMAND);
    }

    @Test
    public void givenAnEmptyCommandNothingHappensThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()).thenReturn(empty());

        applicationWithConsole.start();

        verifyZeroInteractions(application);
    }

    @Test
    public void givenAnCommandThatHasLengthZeroThereIsNoInteractionWithTheApplication() {
        when(console.getNextCommand()).thenReturn(of(""));

        applicationWithConsole.start();

        verifyZeroInteractions(application);
    }
}
