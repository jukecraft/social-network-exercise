package org.twitterconsole.action;

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
import org.twitterconsole.application.TwitterFactory;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;

public class ApplicationAcceptanceTest {
    private Instant instantReturnedByClock = now();
    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);
    private Actions actions;

    @Before
    public void setUpApplication() {
        Clock clock = mock(Clock.class);

        when(clock.getZone()).thenReturn(systemDefault());
        when(clock.instant()).thenAnswer(x -> instantReturnedByClock);

        actions = new TwitterFactory() //
            .withConsole(console) //
            .withClock(clock) //
            .getActions();
    }

    @Test
    public void usersCanPublishMessagesReadTheirTimelineFollowOthersAndViewTheirWall() {
        execute("Alice -> I love the weather today");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(3));
        execute("Bob -> Damn! We lost!");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        execute("Bob -> Good game though.");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        execute("Charlie -> I'm in New York today! Anyone want to have a coffee?");
        instantReturnedByClock = instantReturnedByClock.plus(ofSeconds(2));

        verifyZeroInteractions(console);

        execute("Alice");
        verify(console).print(argThat(contains( //
            "I love the weather today (5 minutes ago)")));

        execute("Bob");
        verify(console).print(argThat(contains( //
            "Good game though. (1 minute ago)", //
            "Damn! We lost! (2 minutes ago)")));

        execute("Charlie follows Alice");
        verifyNoMoreInteractions(console);

        execute("Charlie wall Charlie");
        verify(console).print(argThat(contains( //
            "Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)", //
            "Alice - I love the weather today (5 minutes ago)")));

        execute("Charlie follows Bob");
        verifyNoMoreInteractions(console);

        instantReturnedByClock = instantReturnedByClock.plus(ofSeconds(13));

        execute("Charlie wall Charlie");
        verify(console).print(argThat(contains( //
            "Charlie - I'm in New York today! Anyone want to have a coffee? (15 seconds ago)", //
            "Bob - Good game though. (1 minute ago)", //
            "Bob - Damn! We lost! (2 minutes ago)", //
            "Alice - I love the weather today (5 minutes ago)")));
    }

    private void execute(String command) {
        actions.execute(new Command(command));
    }
}
