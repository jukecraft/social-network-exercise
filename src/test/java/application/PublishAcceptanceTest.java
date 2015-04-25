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

public class PublishAcceptanceTest {
    private SocialNetworkingApplication socialNetworkingApplication;
    private Instant instantReturnedByClock = now();

    @Before
    public void setUpApplication() {
        Clock clock = mock(Clock.class);
        when(clock.getZone()).thenReturn(systemDefault());
        when(clock.instant()).thenAnswer(x -> instantReturnedByClock);
        socialNetworkingApplication = new SocialNetworkingApplication(clock);
    }

    @Test
    public void givenAlicePublishedAMessageNoOutputIsGiven() {
        socialNetworkingApplication.accept("Alice -> I love the weather today");

        List<String> output = socialNetworkingApplication.getOutput();

        assertThat(output, is(empty()));
    }

    @Test
    public void givenAlicePublishedAMessageWhenSomeoneViewsTheirTimelineItIsShownWithTheTimeSincePosted() {
        socialNetworkingApplication.accept("Alice -> I love the weather today");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(5));

        socialNetworkingApplication.accept("Alice");

        List<String> output = socialNetworkingApplication.getOutput();
        assertThat(output, contains("I love the weather today (5 minutes ago)"));
    }

    @Test
    public void givenBobPublishedTwoMessagesWhenSomeoneViewsTheirTimelineTheyAreShownNewestFirst() {
        socialNetworkingApplication.accept("Bob -> Damn! We lost!");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        socialNetworkingApplication.accept("Bob -> Good game though.");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));

        socialNetworkingApplication.accept("Bob");

        List<String> output = socialNetworkingApplication.getOutput();
        assertThat(output, contains("Good game though. (1 minute ago)", "Damn! We lost! (2 minutes ago)"));
    }

    @Test
    @Ignore
    public void givenBobAndAlicePublishedMessagesWhenAliceViewsHerTimelineOnlyHerPostsAreShown() {
        socialNetworkingApplication.accept("Alice -> I love the weather today");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(3));
        socialNetworkingApplication.accept("Bob -> Damn! We lost!");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));
        socialNetworkingApplication.accept("Bob -> Good game though.");
        instantReturnedByClock = instantReturnedByClock.plus(ofMinutes(1));

        socialNetworkingApplication.accept("Alice");

        List<String> output = socialNetworkingApplication.getOutput();
        assertThat(output, contains("I love the weather today (5 minutes ago)"));
    }

}
