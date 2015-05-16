package command.available;

import static io.CommandBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static posts.UserBuilder.aUser;
import io.Command;
import network.TimelineService;

import org.junit.Test;

import posts.User;
import action.available.FollowAction;

public class FollowCommandTest {
    private static final String USERNAME_ALICE = "Alice";
    private static final User ALICE = aUser().withName(USERNAME_ALICE).create();
    private static final String USERNAME_BOB = "Bob";
    private static final User BOB = aUser().withName(USERNAME_BOB).create();

    private TimelineService timelineService = mock(TimelineService.class);
    private FollowAction action = new FollowAction(timelineService);

    @Test
    public void itIsExecutableIfCommandStartsWithFollows() {
        Command command = aCommand().withCommand(" follows ").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(true));
    }

    @Test
    public void itIsNotExecutableIfCommandDoesntStartWithFollows() {
        Command command = aCommand().withCommand(" -> is as follows ").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(false));
    }

    @Test
    public void itRegisteresWithTimelinesThatAliceIsFollowingBob() {
        action.execute(aCommand() //
            .withUser(USERNAME_ALICE) //
            .withCommand(" follows " + USERNAME_BOB) //
            .create());

        verify(timelineService).registerFollowing(ALICE, BOB);
    }

    @Test
    public void itRegisteresWithTimelinesThatBobIsFollowingAlice() {
        action.execute(aCommand() //
            .withUser(USERNAME_BOB) //
            .withCommand(" follows " + USERNAME_ALICE) //
            .create());

        verify(timelineService).registerFollowing(BOB, ALICE);
    }

}
