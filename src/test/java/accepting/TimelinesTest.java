package accepting;

import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TimelinesTest {
    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Timelines timelines = new Timelines();

        Timeline alicesTimeline = timelines.getPostsFor("Alice");

        assertThat(alicesTimeline.printTimeline(aTime().create()), is(empty()));
    }

}
