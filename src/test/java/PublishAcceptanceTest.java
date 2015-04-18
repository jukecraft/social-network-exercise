import static java.time.Clock.fixed;
import static java.time.Clock.tick;
import static java.time.Duration.ofMinutes;
import static java.time.Instant.now;
import static java.time.ZoneId.systemDefault;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.time.Clock;

import org.junit.Test;

public class PublishAcceptanceTest {

	@Test
	public void givenAlicePublishedAMessageWhenSomeoneViewsHerTimelineItIsShownWithTheTimeSincePosted() {
		Clock clock = fixed(now(), systemDefault());
		SocialNetworkingApplication socialNetworkingApplication = new SocialNetworkingApplication(
				clock);
		socialNetworkingApplication.accept("Alice -> I love the weather today");
		tick(clock, ofMinutes(5));

		socialNetworkingApplication.accept("Alice");
		String output = socialNetworkingApplication.getNextLineOfOutput();

		assertThat(output, is("I love the weather today (5 minutes ago)"));
	}
}
