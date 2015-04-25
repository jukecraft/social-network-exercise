package application;

import static java.time.Duration.ofMinutes;
import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SocialNetworkingAcceptanceTest {
    private SocialNetworkingApplication application;
    private Instant instantReturnedByClock = now();

    @Before
    public void setUpApplication() {
        Clock clock = mock(Clock.class);
        when(clock.getZone()).thenReturn(systemDefault());
        when(clock.instant()).thenAnswer(x -> instantReturnedByClock);
        application = new SocialNetworkingApplication(clock);
    }

    @Test
    @Ignore
    public void givenAliceAndBobPublishedMessagesWhenTheyLookAtTheirWallsTheirPostsAreListedNewestFirst() {
        application.accept("Alice -> I love the weather today");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(3));
        application.accept("Bob -> Damn! We lost!");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        application.accept("Bob -> Good game though.");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));

        List<String> output = application.getOutput();
        assertThat(output, is(empty()));

        application.accept("Alice");
        output = application.getOutput();
        assertThat(output, contains("I love the weather today (5 minutes ago)"));

        application.accept("Bob");
        output = application.getOutput();
        assertThat(output, contains("Good game though. (1 minute ago)", "Damn! We lost! (2 minutes ago)"));
    }

}
