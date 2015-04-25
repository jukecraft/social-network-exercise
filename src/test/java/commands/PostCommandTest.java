package commands;

import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;
import timeline.Post;
import timeline.Timelines;

public class PostCommandTest {
    private static final SocialTime TIME = aTime().create();
    private static final User ALICE = aUser().withName("Alice").create();
    private Timelines timelines = mock(Timelines.class);
    private PostCommand command = new PostCommand(timelines);

    @Test
    public void itCreatesANewPostInTheTimelinesFromTheGivenMessageUserAndTime() {
        command.executeCommand(ALICE, "-> I love the weather today", TIME);

        Post expectedPost = aPost() //
            .withMessage("I love the weather today") //
            .withPostingTime(TIME) //
            .create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itCreatesANewPostInTheTimelinesFromTheGivenDifferentMessageUserAndTime() {
        command.executeCommand(ALICE, "-> Good game though.", TIME);

        Post expectedPost = aPost() //
            .withMessage("Good game though.") //
            .withPostingTime(TIME) //
            .create();
        verify(timelines).post(ALICE, expectedPost);
    }

    @Test
    public void itIsApplicableIfItContainsAnArrow() {
        assertThat(command.isApplicable("->"), is(true));
    }

    @Test
    public void itIsNotApplicableIfItDoesntContainAnArrow() {
        assertThat(command.isApplicable("??"), is(false));
    }

    @Test
    public void itReturnsNoOutput() {
        assertThat(command.executeCommand(ALICE, "-> dsg", TIME).getOutput(), is(empty()));
    }
}
