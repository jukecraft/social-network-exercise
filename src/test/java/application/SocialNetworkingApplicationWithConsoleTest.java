package application;

import static application.ApplicationFactory.standardConfiguration;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class SocialNetworkingApplicationWithConsoleTest {
    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();

    @Test
    public void givenTheApplicationWasStartedAWelcomeMessageIsShown() {
        SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);

        SocialNetworkingApplicationWithConsole applicationWithConsole = new SocialNetworkingApplicationWithConsole(
            standardConfiguration().withConsole(console));

        applicationWithConsole.start();

        verify(console).print("Welcome to my social network application");
    }

    @Test
    public void givenAnInputItRoutesThatInputToTheSocialNetworkingApplication() {
        String command = "Alice -> I love the weather today";
        SocialNetworkingApplication application = mock(SocialNetworkingApplication.class);
        SocialNetworkingApplicationWithConsole applicationWithConsole = new SocialNetworkingApplicationWithConsole(
            standardConfiguration().withApplication(application));
        applicationWithConsole.start();

        input.provideText(command);

        verify(application).accept("Alice -> I love the weather today");
    }
}
