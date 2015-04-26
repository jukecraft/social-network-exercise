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

import commands.User;

public class TimelineServiceTest {
    private static final User USER = aUser().create();
    private static final Post POST = aPost().create();
    private static final SocialTime TIME = aTime().create();
    private static final Output OUTPUT = anOutput().create();
    private static final Message MESSAGE = new Message(aPostCommand().create());

    private SocialTimeClock clock = mock(SocialTimeClock.class);
    private Timelines timelines = mock(Timelines.class);
    private TimelineService timelineService = new TimelineService(timelines, clock);

    @Test
    public void itDelegatesPostsToTimelinesIncludingTheCurrentTime() {
        when(clock.getLocalDateTime()).thenReturn(TIME);

        timelineService.post(USER, MESSAGE);

        Post expectedPost = aPost().withMessage(MESSAGE.toString()).withPostingTime(TIME).create();
        verify(timelines).post(USER, expectedPost);
    }

    @Test
    public void itDelegatesPrintTimelineToTimelines() {
        when(timelines.collectPosts(USER)).thenReturn(OUTPUT);

        Output actualOutput = timelineService.collectPosts(USER);

        assertThat(actualOutput, is(OUTPUT));
    }
}
