package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.builder.OutputBuilder.anEmptyOutput;
import static timeline.builder.OutputBuilder.anOutput;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Test;

import timeline.Output;
import timeline.TimelineService;
import timeline.User;

public class WallCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final CommandParameter WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final Output OUTPUT = anOutput().create();

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
        when(timelineService.collectWall(ALICE)).thenReturn(new Output());

        Output output = command.executeCommand(WALL_COMMAND);

        assertThat(output, is(anEmptyOutput().create()));
    }

}
