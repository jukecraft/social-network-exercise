package network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static posts.PostBuilder.anotherPost;
import static posts.PostBuilder.onePost;
import static posts.UserBuilder.aUserNamedAlice;
import static posts.UserBuilder.aUserNamedBob;
import static posts.output.PostsOutputBuilder.anEmptyPostsOutput;
import static posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Test;

import posts.User;
import posts.output.PostsOutput;
import posts.output.WallOutput;

public class FollowingTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();

    private Timelines timelines = mock(Timelines.class);

    @Test
    public void itCollectsTimelinesFromUserAndFollowingIntoAWall() {
        Following following = new Following();
        following.addFollowing(BOB);

        PostsOutput alicesTimeline = anEmptyPostsOutput() //
            .withPost(onePost()) //
            .create();
        when(timelines.collectPosts(ALICE)).thenReturn(alicesTimeline);

        PostsOutput bobsTimeline = anEmptyPostsOutput() //
            .withPost(anotherPost()) //
            .create();
        when(timelines.collectPosts(BOB)).thenReturn(bobsTimeline);

        WallOutput actualOutput = following.collectWallOutput(timelines, ALICE);

        assertThat(actualOutput, is(anEmptyWallOutput() //
            .withTimeline(ALICE, alicesTimeline) //
            .withTimeline(BOB, bobsTimeline) //
            .create()));
    }
}
