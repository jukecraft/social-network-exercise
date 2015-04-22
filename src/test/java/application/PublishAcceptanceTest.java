package application;

import static java.time.Duration.ofMinutes;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PublishAcceptanceTest {
    private SocialNetworkingApplication socialNetworkingApplication;
    private Clock clock;
    private Instant instant = Instant.now();

    @Before
    public void setUpApplication() {
        clock = new Clock() {

            @Override
            public Clock withZone(ZoneId zone) {
                return this;
            }

            @Override
            public Instant instant() {
                return instant;
            }

            @Override
            public ZoneId getZone() {
                return systemDefaultZone().getZone();
            }
        };
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
        instant = instant.plus(ofMinutes(5));

        socialNetworkingApplication.accept("Alice");

        List<String> output = socialNetworkingApplication.getOutput();
        assertThat(output, contains("I love the weather today (5 minutes ago)"));
    }
}
