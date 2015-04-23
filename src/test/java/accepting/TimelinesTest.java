package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.contains;
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

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        Timelines timelines = new Timelines();

        Post post = aPost().create();
        timelines.post("Alice", post);

        Timeline alicesTimeline = timelines.getPostsFor("Alice");
        SocialTime printingTime = aTime().create();
        assertThat(alicesTimeline.printTimeline(printingTime), contains(post.printAt(printingTime)));
    }

}
