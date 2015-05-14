package application;

import static application.ApplicationFactory.standardConfiguration;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class SocialNetworkingApplicationWithConsoleTest {
    @Test
    public void givenTheApplicationWasStartedAWelcomeMessageIsShown() {
        SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);

        SocialNetworkingApplicationWithConsole application = new SocialNetworkingApplicationWithConsole(
            standardConfiguration().withConsole(console));

        application.start();

        verify(console).print("Welcome to my social network application");
    }
}
