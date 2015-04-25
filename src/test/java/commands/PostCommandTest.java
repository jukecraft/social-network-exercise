package commands;

import static accepting.UserBuilder.aUser;
import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import accepting.Post;
import accepting.SocialTime;
import accepting.Timelines;
import accepting.User;

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
}
