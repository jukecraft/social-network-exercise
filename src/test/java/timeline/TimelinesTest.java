package timeline;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.builder.PostBuilder.aPost;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Test;

public class TimelinesTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final User BOB = aUser().withName("Bob").create();
    private static final Post A_POST = aPost().withMessage("a post").create();
    private static final Post ANOTHER_POST = aPost().withMessage("another post").create();

    private Timelines timelines = new Timelines();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(ALICE, A_POST);

        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .create()));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(ALICE, A_POST);
        timelines.post(BOB, ANOTHER_POST);

        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .create()));
    }

}
