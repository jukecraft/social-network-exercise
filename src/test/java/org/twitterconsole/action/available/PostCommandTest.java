package org.twitterconsole.action.available;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.Message;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;
import org.twitterconsole.time.SocialNetworkingClock;

public class PostCommandTest {
    private static final User ALICE = aUserNamedAlice();
    private static final SocialTime TIME = aTime().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
    private PostAction action = new PostAction(timelineService, clock);

    @Before
    public void setUp() {
        when(clock.getLocalDateTime()).thenReturn(TIME);
    }

    @Test
    public void itPostsANewMessageInTheTimelinesForTheGivenUser() {
        Command command = aCommand().withCommand(" -> I love the weather today").create();

        action.execute(command);

        verify(timelineService).post(ALICE, new Message(command), TIME);
    }

    @Test
    public void itPostsADifferentNewMessageInTheTimelinesForTheGivenUser() {
        Command command = aCommand().withCommand(" -> Good game though.").create();

        action.execute(command);

        verify(timelineService).post(ALICE, new Message(command), TIME);
    }

    @Test
    public void itIsExecutableIfCommandStartsWithAnArrow() {
        Command command = aCommand().withCommand(" -> ").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(true));
    }

    @Test
    public void itIsNotExecutableIfCommandDoesntStartWithAnArrow() {
        Command command = aCommand().withCommand(" follows ->").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(false));
    }

}
