package commands;

import static commands.CommandParameterBuilder.aCommand;
import static commands.CommandParameterBuilder.aPostCommand;
import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static timeline.OutputBuilder.anEmptyOutput;

import org.junit.Test;

import timeline.Message;
import timeline.Output;
import timeline.TimelineService;

public class PostCommandTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private TimelineService timelines = mock(TimelineService.class);
    private PostCommand command = new PostCommand(timelines);

    @Test
    public void itPostANewMessageInTheTimelinesForTheGivenUser() {
        CommandParameter commandParameter = aCommand().withCommand(" -> I love the weather today").create();

        command.executeCommand(ALICE, commandParameter);

        verify(timelines).post(ALICE, new Message(commandParameter));
    }

    @Test
    public void itPostADifferentNewMessageInTheTimelinesForTheGivenUser() {
        CommandParameter commandParameter = aCommand().withCommand(" -> Good game though.").create();

        command.executeCommand(ALICE, commandParameter);

        verify(timelines).post(ALICE, new Message(commandParameter));
    }

    @Test
    public void itIsApplicableIfItStartsWithAnArrow() {
        CommandParameter commandParameter = aCommand().withCommand(" -> ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfItDoesntContainAnArrow() {
        CommandParameter commandParameter = aCommand().withCommand("").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itIsNotApplicableIfItDoesntStartWithAnArrow() {
        CommandParameter commandParameter = aCommand().withCommand(" follows ->").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }

    @Test
    public void itReturnsNoOutput() {
        CommandParameter commandParameter = aPostCommand().create();

        Output output = command.executeCommand(ALICE, commandParameter);

        assertThat(output, is(anEmptyOutput().create()));
    }
}
