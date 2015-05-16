package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.SocialTimeBuilder.aTime;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import timeline.Message;
import timeline.TimelineService;
import timeline.User;

public class PostCommandTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final SocialTime TIME = aTime().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private SocialTimeClock clock = mock(SocialTimeClock.class);
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
