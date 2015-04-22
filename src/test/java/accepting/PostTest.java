package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostTest {
    private static final String MESSAGE_TEXT = "I love the weather today";
    private static final String ANOTHER_MESSAGE_TEXT = "Damn! We lost!";

    @Test
    public void createdWithFilledMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost() //
            .withMessage(MESSAGE_TEXT) //
            .withPostingTime(aTime()) //
            .create();

        String printedTimestamp = post.printTimestamp(aTime() //
            .plusMinutes(5) //
            .create());

        assertThat(printedTimestamp, is(MESSAGE_TEXT + " (5 minutes ago)"));
    }

    @Test
    public void createdWithOtherMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost() //
            .withMessage(ANOTHER_MESSAGE_TEXT) //
            .withPostingTime(aTime()) //
            .create();

        String printedTimestamp = post.printTimestamp(aTime() //
            .plusMinutes(5) //
            .create());

        assertThat(printedTimestamp, is(ANOTHER_MESSAGE_TEXT + " (5 minutes ago)"));
    }

    @Test
    public void createdWithMessageAndOtherPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost() //
            .withMessage(MESSAGE_TEXT) //
            .withPostingTime(aTime()) //
            .create();

        String printedTimestamp = post.printTimestamp(aTime() //
            .plusMinutes(10) //
            .create());

        assertThat(printedTimestamp, is(MESSAGE_TEXT + " (10 minutes ago)"));
    }

}
