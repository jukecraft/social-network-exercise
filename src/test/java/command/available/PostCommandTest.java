package command.available;

import static io.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static posts.SocialTimeBuilder.aTime;
import static posts.UserBuilder.aUserNamedAlice;
import io.CommandParameter;
import network.TimelineService;

import org.junit.Before;
import org.junit.Test;

import posts.Message;
import posts.SocialTime;
import posts.User;
import time.SocialNetworkingClock;

public class PostCommandTest {
    private static final User ALICE = aUserNamedAlice();
    private static final SocialTime TIME = aTime().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
    private PostCommand command = new PostCommand(timelineService, clock);

    @Before
    public void setUp() {
        when(clock.getLocalDateTime()).thenReturn(TIME);
    }

    @Test
    public void itPostANewMessageInTheTimelinesForTheGivenUser() {
        CommandParameter commandParameter = aCommand().withCommand(" -> I love the weather today").create();

        command.executeCommand(commandParameter);

        verify(timelineService).post(ALICE, new Message(commandParameter), TIME);
    }

    @Test
    public void itPostADifferentNewMessageInTheTimelinesForTheGivenUser() {
        CommandParameter commandParameter = aCommand().withCommand(" -> Good game though.").create();

        command.executeCommand(commandParameter);

        verify(timelineService).post(ALICE, new Message(commandParameter), TIME);
    }

    @Test
    public void itIsApplicableIfItStartsWithAnArrow() {
        CommandParameter commandParameter = aCommand().withCommand(" -> ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfItDoesntStartWithAnArrow() {
        CommandParameter commandParameter = aCommand().withCommand(" follows ->").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

}
