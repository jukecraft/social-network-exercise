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

public class TimelineCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final CommandParameter TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final Output OUTPUT = anOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private TimelineCommand command = new TimelineCommand(timelineService);

    @Test
    public void itReturnsNoOutputIfTimelinesHasNoTimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(new Output());

        Output output = command.executeCommand(TIMELINE_COMMAND);

        assertThat(output, is(anEmptyOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasATimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(OUTPUT);

        Output output = command.executeCommand(TIMELINE_COMMAND);

        assertThat(output, is(OUTPUT));
    }

    @Test
    public void itIsApplicableIfItTheCommandIsEmpty() {
        assertThat(command.isApplicable(TIMELINE_COMMAND), is(true));
    }

    @Test
    public void itIsNotApplicableIfTheCommandIsNotEmpty() {
        CommandParameter commandParameter = aCommand() //
            .withCommand(" -> ") //
            .create();

        assertThat(command.isApplicable(commandParameter), is(false));
    }
}
