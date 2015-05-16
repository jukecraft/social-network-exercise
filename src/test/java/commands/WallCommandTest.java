package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.builder.UserBuilder.aUser;
import static timeline.builder.WallOutputBuilder.aWallOutput;
import static timeline.builder.WallOutputBuilder.anEmptyWallOutput;
import io.CommandParameter;

import org.junit.Test;

import timeline.Output;
import timeline.TimelineService;
import timeline.User;
import timeline.WallOutput;

public class WallCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final CommandParameter WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private WallCommand command = new WallCommand(timelineService);

    @Test
    public void itIsApplicableIfCommandStartsWithWall() {
        CommandParameter commandParameter = aCommand().withCommand(" wall").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfCommandDoesntStartWithWall() {
        CommandParameter commandParameter = aCommand().withCommand(" -> is a wall").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itReturnsNoOutputIfTimelinesHasNoWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(anEmptyWallOutput().create());

        Output output = command.executeCommandWithOutput(WALL_COMMAND);

        assertThat(output, is(anEmptyWallOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasAWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(OUTPUT);

        WallOutput output = command.executeCommandWithOutput(WALL_COMMAND);

        assertThat(output, is(OUTPUT));
    }

}
