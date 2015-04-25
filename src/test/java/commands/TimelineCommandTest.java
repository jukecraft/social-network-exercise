package commands;

import static commands.UserBuilder.aUser;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import timeline.Output;
import timeline.Timelines;

public class TimelineCommandTest {
    private static final SocialTime TIME = aTime().create();
    private static final User ALICE = aUser().withName("Alice").create();
    private static final Output OUTPUT = new Output(asList("output", "another output"));
    private Timelines timelines = mock(Timelines.class);
    private TimelineCommand command = new TimelineCommand(timelines);

    @Test
    public void itReturnsNoOutputIfTimelinesHasNoTimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(new Output());

        Output output = command.executeCommand(ALICE, "", TIME);

        assertThat(output.getOutput(), is(empty()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasATimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(OUTPUT);

        Output output = command.executeCommand(ALICE, "", TIME);

        assertThat(output, is(OUTPUT));
    }

    @Test
    public void itIsApplicableIfItTheCommandIsEmpty() {
        assertThat(command.isApplicable(""), is(true));
    }

    @Test
    public void itIsNotApplicableIfTheCommandIsNotEmpty() {
        assertThat(command.isApplicable("?"), is(false));
    }
}
