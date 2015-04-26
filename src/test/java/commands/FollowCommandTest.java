package commands;

import static commands.CommandParameterBuilder.aCommand;
import static commands.CommandParameterBuilder.aFollowsCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Test;

import timeline.PostsOutput;
import timeline.TimelineService;
import timeline.User;

public class FollowCommandTest {
    private static final String USERNAME_ALICE = "Alice";
    private static final User ALICE = aUser().withName(USERNAME_ALICE).create();
    private static final String USERNAME_BOB = "Bob";
    private static final User BOB = aUser().withName(USERNAME_BOB).create();

    private TimelineService timelineService = mock(TimelineService.class);
    private FollowCommand command = new FollowCommand(timelineService);

    @Test
    public void itIsApplicableIfCommandStartsWithFollows() {
        CommandParameter commandParameter = aCommand().withCommand(" follows ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfCommandDoesntStartWithFollows() {
        CommandParameter commandParameter = aCommand().withCommand(" -> is as follows ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itRegisteresWithTimelinesThatAliceIsFollowingBob() {
        command.executeCommand(aCommand() //
            .withUser(USERNAME_ALICE) //
            .withCommand(" follows " + USERNAME_BOB) //
            .create());

        verify(timelineService).registerFollowing(ALICE, BOB);
    }

    @Test
    public void itRegisteresWithTimelinesThatBobIsFollowingAlice() {
        command.executeCommand(aCommand() //
            .withUser(USERNAME_BOB) //
            .withCommand(" follows " + USERNAME_ALICE) //
            .create());

        verify(timelineService).registerFollowing(BOB, ALICE);
    }

    @Test
    public void itReturnsNoOutput() {
        CommandParameter commandParameter = aFollowsCommand().create();

        PostsOutput output = command.executeCommand(commandParameter);

        assertThat(output, is(anEmptyPostsOutput().create()));
    }
}
