package org.twitterconsole.network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.PostBuilder.aPost;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;
import static org.twitterconsole.posts.UserBuilder.aUserNamedBob;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class TimelinesTest {
    private static final User ALICE = aUserNamedAlice();
    private static final Post A_POST_FROM_ALICE = aPost().withAuthor(ALICE).create();
    private static final Post A_POST_FROM_BOB = aPost().withAuthor(aUserNamedBob()).create();

    private Timelines timelines = new Timelines();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(A_POST_FROM_ALICE);

        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput()
            .withPost(A_POST_FROM_ALICE)
            .create()));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(A_POST_FROM_ALICE);
        timelines.post(A_POST_FROM_BOB);

        PostsOutput alicesTimeline = timelines.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput()
            .withPost(A_POST_FROM_ALICE)
            .create()));
    }

}
