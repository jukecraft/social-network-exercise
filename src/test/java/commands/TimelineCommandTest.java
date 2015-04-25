package commands;

import static accepting.UserBuilder.aUser;
import static accepting.builder.SocialTimeBuilder.aTime;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import accepting.SocialTime;
import accepting.Timelines;
import accepting.User;

public class TimelineCommandTest {
    private static final SocialTime TIME = aTime().create();
    private static final User ALICE = aUser().withName("Alice").create();
    private static final List<String> OUTPUT = asList("output", "another output");
    private Timelines timelines = mock(Timelines.class);
    private TimelineCommand command = new TimelineCommand(timelines);

    @Test
    public void itReturnsNoOutputIfTimelinesHasNoTimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(emptyList());

        List<String> output = command.executeCommand(ALICE, "", TIME);

        assertThat(output, is(empty()));
    }

    @Test
    public void itReturnsOutputIfTimelinesHasATimelineForTheGivenUser() {
        when(timelines.printTimeline(ALICE, TIME)).thenReturn(OUTPUT);

        List<String> output = command.executeCommand(ALICE, "", TIME);

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
