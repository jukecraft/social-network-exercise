package command.available;

import static io.CommandBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static posts.UserBuilder.aUser;
import static posts.output.WallOutputBuilder.aWallOutput;
import static posts.output.WallOutputBuilder.anEmptyWallOutput;
import io.Command;
import network.TimelineService;

import org.junit.Test;

import posts.User;
import posts.output.Output;
import posts.output.WallOutput;
import action.available.WallAction;

public class WallCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private WallAction action = new WallAction(timelineService);

    @Test
    public void itIsExecutableIfCommandStartsWithWall() {
        Command command = aCommand().withCommand(" wall").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(true));
    }

    @Test
    public void itIsNotExecutableIfCommandDoesntStartWithWall() {
        Command command = aCommand().withCommand(" -> is a wall").create();

        boolean isExecutable = action.isExecutable(command);

        assertThat(isExecutable, is(false));
    }

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(anEmptyWallOutput().create());

        Output output = action.executeWithOutput(WALL_COMMAND);

        assertThat(output, is(anEmptyWallOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasAWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(OUTPUT);

        WallOutput output = action.executeWithOutput(WALL_COMMAND);

        assertThat(output, is(OUTPUT));
    }

}
