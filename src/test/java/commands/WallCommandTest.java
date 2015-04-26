package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;
import static timeline.builder.PostsOutputBuilder.aPostsOutput;
import static timeline.builder.UserBuilder.aUser;

import org.junit.Test;

import timeline.PostsOutput;
import timeline.TimelineService;
import timeline.User;

public class WallCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final CommandParameter WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();

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
        when(timelineService.collectWall(ALICE)).thenReturn(new PostsOutput());

        PostsOutput output = command.executeCommand(WALL_COMMAND);

        assertThat(output, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasAWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(OUTPUT);

        PostsOutput output = command.executeCommand(WALL_COMMAND);

        assertThat(output, is(OUTPUT));
    }

}
