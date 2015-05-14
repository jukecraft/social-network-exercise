package application;

import static application.ApplicationFactory.standardConfiguration;
import static java.lang.System.getProperty;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class SocialNetworkingApplicationWithConsoleTest {
    private static final String A_COMMAND = "Alice -> I love the weather today";
    private static final String LINE_SEPERATOR = getProperty("line.separator");

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
        SocialNetworkingApplication application = mock(SocialNetworkingApplication.class);
        SocialNetworkingApplicationWithConsole applicationWithConsole = new SocialNetworkingApplicationWithConsole(
            standardConfiguration().withApplication(application));
        input.provideText(A_COMMAND + LINE_SEPERATOR);

        applicationWithConsole.start();

        verify(application).accept(A_COMMAND);
    }

}
