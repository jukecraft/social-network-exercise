import static java.time.Clock.fixed;
import static java.time.Clock.tick;
import static java.time.Duration.ofMinutes;
import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PublishAcceptanceTest {
    private SocialNetworkingApplication socialNetworkingApplication;
    private Clock clock;

    @Before
    public void setUpApplication() {
        clock = fixed(now(), systemDefault());
        socialNetworkingApplication = new SocialNetworkingApplication(clock);
    }

    @Test
    public void givenAlicePublishedAMessageWhenSomeoneViewsTheirTimelineItIsShownWithTheTimeSincePosted() {
        socialNetworkingApplication.accept("Alice -> I love the weather today");
        tick(clock, ofMinutes(5));

        socialNetworkingApplication.accept("Alice");

        List<String> output = socialNetworkingApplication.getOutput();
        assertThat(output, contains("I love the weather today (5 minutes ago)"));
    }
}
