package accepting;

import static accepting.UserBuilder.aUser;
import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostCommandTest {
    private static final SocialTime TIME = aTime().create();

    @Test
    public void whenItParsesACommandFromStringItExtractsTheUser() {
        PostCommand command = new PostCommand("Alice -> I love the weather today");

        assertThat(command.getUser(), is(aUser().withName("Alice").create()));
    }

    @Test
    public void whenItParsesACommandFromAnotherUserItExtractsTheUser() {
        PostCommand command = new PostCommand("Bob -> Good game though.");

        assertThat(command.getUser(), is(aUser().withName("Bob").create()));
    }

    @Test
    public void givenACommandWithAMessageWhenAPostIsCreatedItReturnsANewPostWithThatExtractedMessageAndTheGivenTime() {
        PostCommand command = new PostCommand("Bob -> Good game though.");

        assertThat(command.createPost(TIME), is(aPost() //
            .withMessage("Good game though.") //
            .withPostingTime(TIME) //
            .create()));
    }

    @Test
    public void givenACommandWithADifferentMessageWhenAPostIsCreatedItReturnsANewPostWithThatExtractedMessageAndTheGivenTime() {
        PostCommand command = new PostCommand("Bob -> Damn! We lost!");

        assertThat(command.createPost(TIME), is(aPost() //
            .withMessage("Damn! We lost!") //
            .withPostingTime(TIME) //
            .create()));
    }

}
