package command.available;

import static io.CommandBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static posts.SocialTimeBuilder.aTime;
import static posts.UserBuilder.aUserNamedAlice;
import io.Command;
import network.TimelineService;

import org.junit.Before;
import org.junit.Test;

import posts.Message;
import posts.SocialTime;
import posts.User;
import time.SocialNetworkingClock;
import action.available.PostAction;

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
