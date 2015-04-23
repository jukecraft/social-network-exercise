package accepting;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import accepting.builder.SocialTimeBuilder;

public class TimelinesTest {
    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Timelines timelines = new Timelines();
        Posts alicesPosts = timelines.getPostsFor("Alice");
        assertThat(alicesPosts.printPosts(SocialTimeBuilder.aTime().create()), is(empty()));
    }
}
