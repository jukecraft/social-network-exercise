package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CommandTest {
    private static final SocialTime TIME = aTime().create();

    @Test
    public void whenItParsesACommandFromStringItExtractsTheUser() {
        Command command = new Command("Alice -> I love the weather today");

        assertThat(command.getUser(), is("Alice"));
    }

    @Test
    public void whenItParsesACommandFromAnotherUserItExtractsTheUser() {
        Command command = new Command("Bob -> Good game though.");

        assertThat(command.getUser(), is("Bob"));
    }

    @Test
    public void givenACommandWithAMessageWhenAPostIsCreatedItReturnsANewPostWithThatExtractedMessageAndTheGivenTime() {
        Command command = new Command("Bob -> Good game though.");

        assertThat(command.createPost(TIME), is(aPost() //
            .withMessage("Good game though.") //
            .withPostingTime(TIME) //
            .create()));
    }

    @Test
    public void givenACommandWithADifferentMessageWhenAPostIsCreatedItReturnsANewPostWithThatExtractedMessageAndTheGivenTime() {
        Command command = new Command("Bob -> Damn! We lost!");

        assertThat(command.createPost(TIME), is(aPost() //
            .withMessage("Damn! We lost!") //
            .withPostingTime(TIME) //
            .create()));
    }

}
