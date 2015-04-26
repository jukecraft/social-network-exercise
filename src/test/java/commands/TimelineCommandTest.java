package commands;

import static commands.CommandParameterBuilder.aCommand;
import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import timeline.Output;
import timeline.Timelines;

public class TimelineCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final CommandParameter TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final SocialTime TIME = aTime().create();
    private static final Output OUTPUT = anEmptyOutput().withLine("my line").create();
    private Timelines timelines = mock(Timelines.class);
    private TimelineCommand command = new TimelineCommand(timelines);

    @Test
    public void itReturnsNoOutputIfTimelinesHasNoTimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(new Output());

        Output output = command.executeCommand(ALICE, TIMELINE_COMMAND, TIME);

        assertThat(output, is(anEmptyOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasATimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(OUTPUT);

        Output output = command.executeCommand(ALICE, TIMELINE_COMMAND, TIME);

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