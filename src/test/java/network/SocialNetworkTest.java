package network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static posts.PostBuilder.anotherPost;
import static posts.PostBuilder.onePost;
import static posts.UserBuilder.aUserNamedAlice;
import static posts.UserBuilder.aUserNamedBob;
import static posts.output.PostsOutputBuilder.aPostsOutput;
import static posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Before;
import org.junit.Test;

import posts.User;
import posts.output.PostsOutput;
import posts.output.WallOutput;

public class SocialNetworkTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();
    private static final PostsOutput ALICES_POSTS = aPostsOutput().withPost(onePost()).create();
    private static final PostsOutput BOBS_POSTS = aPostsOutput().withPost(anotherPost()).create();

    private Timelines timelines = mock(Timelines.class);
    private SocialNetwork socialNetwork = new SocialNetwork();

    @Before
    public void setUp() {
        when(timelines.collectPosts(ALICE)).thenReturn(ALICES_POSTS);
        when(timelines.collectPosts(BOB)).thenReturn(BOBS_POSTS);
    }

    @Test
    public void givenNoFollowingWhenAskedForTheWallOutputThenItReturnsOnlyAlicesPosts() {
        WallOutput wall = socialNetwork.collectWallOutput(timelines, ALICE);

        assertThat(wall, is(anEmptyWallOutput() //
            .withTimeline(ALICE, ALICES_POSTS) //
            .create()));
    }

    @Test
    public void givenAliceFollowsBobWhenAskedForTheWallOutputThenItReturnsBothUsersPosts() {
        socialNetwork.registerFollowing(ALICE, BOB);

        WallOutput wall = socialNetwork.collectWallOutput(timelines, ALICE);

        assertThat(wall, is(anEmptyWallOutput() //
            .withTimeline(ALICE, ALICES_POSTS) //
            .withTimeline(BOB, BOBS_POSTS) //
            .create()));
    }
}
