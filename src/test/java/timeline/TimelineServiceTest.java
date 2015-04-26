package timeline;

import static commands.CommandParameterBuilder.aPostCommand;
import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.OutputBuilder.anOutput;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;

public class TimelineServiceTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final SocialTime TIME = aTime().create();
    private static final Output OUTPUT = anOutput().create();
    private static final Message MESSAGE = new Message(aPostCommand().create());

    private Timelines timelines = mock(Timelines.class);
    private SocialTimeClock clock = mock(SocialTimeClock.class);
    private TimelineService timelineService = new TimelineService(timelines, clock);

    @Test
    public void itDelegatesPostsToTimelinesIncludingTheCurrentTime() {
        when(clock.getLocalDateTime()).thenReturn(TIME);

        timelineService.post(ALICE, MESSAGE);

        Post expectedPost = aPost().withMessage(MESSAGE.toString()).withPostingTime(TIME).create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itDelegatesPrintTimelineToTimelines() {
        when(timelines.collectPosts(ALICE)).thenReturn(OUTPUT);

        Output actualOutput = timelineService.collectPosts(ALICE);

        assertThat(actualOutput, is(OUTPUT));
    }

    @Test
    public void itRegisteresANewFollowingWithAUser() {
        User bob = mock(User.class);

        timelineService.registerFollowing(bob, ALICE);

        verify(bob).follows(ALICE);
    }

    @Test
    public void itUpdatesTheUserInTimelines() {
        User bob = aUser().create();

        timelineService.registerFollowing(bob, ALICE);

        verify(timelines).updateUser(bob);

    }
}
