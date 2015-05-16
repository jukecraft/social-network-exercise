package network;

import static io.CommandParameterBuilder.aPostCommand;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static posts.PostBuilder.aPost;
import static posts.SocialTimeBuilder.aTime;
import static posts.UserBuilder.aUserNamedAlice;
import static posts.UserBuilder.aUserNamedBob;
import static posts.output.PostsOutputBuilder.aPostsOutput;
import static posts.output.PostsOutputBuilder.anEmptyPostsOutput;
import static posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Test;

import posts.Message;
import posts.Post;
import posts.SocialTime;
import posts.User;
import posts.output.PostsOutput;
import posts.output.WallOutput;

public class TimelineServiceTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();
    private static final SocialTime TIME = aTime().create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();
    private static final Message MESSAGE = new Message(aPostCommand().create());

    private Timelines timelines = mock(Timelines.class);
    private SocialNetwork network = mock(SocialNetwork.class);

    private TimelineService timelineService = new TimelineService(timelines, network);

    @Test
    public void itDelegatesPostsToTimelinesIncludingTheCurrentTime() {
        timelineService.post(ALICE, MESSAGE, TIME);

        Post expectedPost = aPost() //
            .withMessage(MESSAGE.toString()) //
            .withPostingTime(TIME) //
            .withUser(ALICE) //
            .create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itDelegatesPrintTimelineToTimelines() {
        when(timelines.collectPosts(ALICE)).thenReturn(OUTPUT);

        PostsOutput actualOutput = timelineService.collectPosts(ALICE);

        assertThat(actualOutput, is(OUTPUT));
    }

    @Test
    public void itRegisteresANewFollowingInTheSocialNetwork() {
        timelineService.registerFollowing(BOB, ALICE);

        verify(network).registerFollowing(BOB, ALICE);
    }

    @Test
    public void itCollectsTimelinesFromUserAndFollowingIntoAWall() {
        when(network.getFollowing(ALICE)).thenReturn(asList(BOB));

        PostsOutput alicesTimeline = anEmptyPostsOutput() //
            .withPost(aPost().withMessage("message1").create()) //
            .withPost(aPost().withMessage("message2").create()) //
            .create();
        when(timelines.collectPosts(ALICE)).thenReturn(alicesTimeline);

        PostsOutput bobsTimeline = anEmptyPostsOutput() //
            .withPost(aPost().withMessage("message3").create()) //
            .withPost(aPost().withMessage("message4").create()) //
            .create();
        when(timelines.collectPosts(BOB)).thenReturn(bobsTimeline);

        WallOutput actualOutput = timelineService.collectWall(ALICE);

        assertThat(actualOutput, is(anEmptyWallOutput() //
            .withTimeline(ALICE, alicesTimeline) //
            .withTimeline(BOB, bobsTimeline) //
            .create()));
    }
}
