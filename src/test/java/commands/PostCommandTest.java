package commands;

import static commands.CommandParameterBuilder.aCommand;
import static commands.CommandParameterBuilder.aPostCommand;
import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import timeline.Output;
import timeline.Post;
import timeline.TimelineService;

public class PostCommandTest {
    private static final SocialTime TIME = aTime().create();
    private static final User ALICE = aUser().withName("Alice").create();
    private TimelineService timelines = mock(TimelineService.class);
    private PostCommand command = new PostCommand(timelines);

    @Test
    public void itCreatesANewPostInTheTimelinesFromTheGivenMessageUserAndTime() {
        command.executeCommand(ALICE, aCommand().withCommand(" -> I love the weather today").create(), TIME);

        Post expectedPost = aPost() //
            .withMessage("I love the weather today") //
            .withPostingTime(TIME) //
            .create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itCreatesANewPostInTheTimelinesFromTheGivenDifferentMessageUserAndTime() {
        command.executeCommand(ALICE, aCommand().withCommand(" -> Good game though.").create(), TIME);

        Post expectedPost = aPost() //
            .withMessage("Good game though.") //
            .withPostingTime(TIME) //
            .create();
        verify(timelines).post(ALICE, expectedPost);
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

        Output output = command.executeCommand(ALICE, commandParameter, TIME);

        assertThat(output, is(anEmptyOutput().create()));
    }
}
