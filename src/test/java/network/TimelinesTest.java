package network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static posts.PostBuilder.anotherPost;
import static posts.PostBuilder.onePost;
import static posts.UserBuilder.aUserNamedAlice;
import static posts.UserBuilder.aUserNamedBob;
import static posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;

import posts.Post;
import posts.User;
import posts.output.PostsOutput;

public class TimelinesTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();
    private static final Post A_POST = onePost();
    private static final Post ANOTHER_POST = anotherPost();

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
