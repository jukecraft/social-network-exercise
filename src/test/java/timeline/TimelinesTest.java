package timeline;

import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.PostBuilder.aPost;

import org.junit.Test;

import commands.User;

public class TimelinesTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final User BOB = aUser().withName("Bob").create();
    private static final Post A_POST = aPost().withMessage("a post").create();
    private static final Post ANOTHER_POST = aPost().withMessage("another post").create();

    private Timelines timelines = new Timelines();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Output alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyOutput().create()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(ALICE, A_POST);

        Output alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyOutput() //
            .withPost(A_POST) //
            .create()));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(ALICE, A_POST);
        timelines.post(BOB, ANOTHER_POST);

        Output alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyOutput() //
            .withPost(A_POST) //
            .create()));
    }

}
