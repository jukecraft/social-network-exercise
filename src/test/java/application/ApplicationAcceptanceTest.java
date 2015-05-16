package application;

import static application.ApplicationFactory.standardConfiguration;
import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;
import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

public class ApplicationAcceptanceTest {
    private SocialNetworkingApplication application;
    private Instant instantReturnedByClock = now();
    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);

    @Before
    public void setUpApplication() {
        Clock clock = mock(Clock.class);

        when(clock.getZone()).thenReturn(systemDefault());
        when(clock.instant()).thenAnswer(x -> instantReturnedByClock);
        ApplicationFactory applicationFactory = standardConfiguration().withConsole(console).withClock(clock);
        application = new SocialNetworkingApplication(applicationFactory.getCommands());
    }

    @Test
    public void usersCanPublishMessagesReadTheirTimelineFollowOthersAndViewTheirWall() {
        application.accept("Alice -> I love the weather today");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(3));
        application.accept("Bob -> Damn! We lost!");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        application.accept("Bob -> Good game though.");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        application.accept("Charlie -> I'm in New York today! Anyone want to have a coffee?");
        instantReturnedByClock = instantReturnedByClock.plus(ofSeconds(2));

        verifyZeroInteractions(console);

        application.accept("Alice");
        verify(console).print(argThat(contains( //
            "I love the weather today (5 minutes ago)")));

        application.accept("Bob");
        verify(console).print(argThat(contains( //
            "Good game though. (1 minute ago)", //
            "Damn! We lost! (2 minutes ago)")));

        application.accept("Charlie follows Alice");
        verifyNoMoreInteractions(console);

        application.accept("Charlie wall Charlie");
        verify(console).print(argThat(contains( //
            "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)", //
            "Alice - I love the weather today (5 minutes ago)")));

        application.accept("Charlie follows Bob");
        verifyNoMoreInteractions(console);

        instantReturnedByClock = instantReturnedByClock.plus(ofSeconds(13));

        application.accept("Charlie wall Charlie");
        verify(console).print(argThat(contains( //
            "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)", //
            "Bob - Good game though. (1 minute ago)", //
            "Bob - Damn! We lost! (2 minutes ago)", //
            "Alice - I love the weather today (5 minutes ago)")));
    }
}
