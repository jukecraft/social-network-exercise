package commands;

import static commands.CommandParameterBuilder.aCommand;
import static commands.CommandParameterBuilder.aFollowsCommand;
import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import timeline.Output;
import timeline.TimelineService;

public class FollowCommandTest {
    private static final SocialTime TIME = aTime().create();
    private static final String USERNAME_ALICE = "Alice";
    private static final User ALICE = aUser().withName(USERNAME_ALICE).create();
    private static final String USERNAME_BOB = "Bob";
    private static final User BOB = aUser().withName(USERNAME_BOB).create();
    private TimelineService timelines = mock(TimelineService.class);
    private FollowCommand command = new FollowCommand(timelines);

    @Test
    public void itIsApplicableIfCommandContainsFollows() {
        CommandParameter commandParameter = aCommand().withCommand(" follows ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfCommandDoesntContainFollows() {
        CommandParameter commandParameter = aCommand().withCommand("").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itIsNotApplicableIfCommandDoesntStartWithFollows() {
        CommandParameter commandParameter = aCommand().withCommand(" -> is as follows ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itRegisteresWithTimelinesThatAliceIsFollowingBob() {
        command.executeCommand(ALICE, aCommand() //
            .withUser(USERNAME_ALICE) //
            .withCommand(" follows " + USERNAME_BOB) //
            .create(), TIME);

        verify(timelines).registerFollowing(ALICE, BOB);
    }

    @Test
    public void itRegisteresWithTimelinesThatBobIsFollowingAlice() {
        command.executeCommand(BOB, aCommand() //
            .withUser(USERNAME_BOB) //
            .withCommand(" follows " + USERNAME_ALICE) //
            .create(), TIME);

        verify(timelines).registerFollowing(BOB, ALICE);
    }

    @Test
    public void itReturnsNoOutput() {
        CommandParameter commandParameter = aFollowsCommand().create();

        Output output = command.executeCommand(ALICE, commandParameter, TIME);

        assertThat(output, is(anEmptyOutput().create()));
    }
}
