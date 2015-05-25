package org.twitterconsole.network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.twitterconsole.posts.PostBuilder.anotherPost;
import static org.twitterconsole.posts.PostBuilder.onePost;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;
import static org.twitterconsole.posts.UserBuilder.aUserNamedBob;
import static org.twitterconsole.posts.output.PostsOutputBuilder.aPostsOutput;
import static org.twitterconsole.posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;
import org.twitterconsole.posts.output.WallOutput;

public class UserRepositoryTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();
    private static final PostsOutput ALICES_POSTS = aPostsOutput().withPost(onePost()).create();
    private static final PostsOutput BOBS_POSTS = aPostsOutput().withPost(anotherPost()).create();

    private PostRepository postRepository = mock(PostRepository.class);
    private UserRepository userRepository = new UserRepository();

    @Before
    public void setUp() {
        when(postRepository.collectPosts(ALICE)).thenReturn(ALICES_POSTS);
        when(postRepository.collectPosts(BOB)).thenReturn(BOBS_POSTS);
    }

    @Test
    public void givenNoFollowingWhenAskedForTheWallOutputThenItReturnsOnlyAlicesPosts() {
        WallOutput wall = userRepository.collectWallOutput(postRepository, ALICE);

        assertThat(wall, is(anEmptyWallOutput()
            .withTimeline(ALICE, ALICES_POSTS)
            .create()));
    }

    @Test
    public void givenAliceFollowsBobWhenAskedForTheWallOutputThenItReturnsBothUsersPosts() {
        userRepository.registerFollowing(ALICE, BOB);

        WallOutput wall = userRepository.collectWallOutput(postRepository, ALICE);

        assertThat(wall, is(anEmptyWallOutput()
            .withTimeline(ALICE, ALICES_POSTS)
            .withTimeline(BOB, BOBS_POSTS)
            .create()));
    }
}
