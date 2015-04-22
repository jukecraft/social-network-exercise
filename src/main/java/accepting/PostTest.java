package accepting;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

public class PostTest {
    private static final LocalDateTime TIMESTAMP = now();
    private static final String MESSAGE_TEXT = "I love the weather today";
    private static final String ANOTHER_MESSAGE_TEXT = "Damn! We lost!";

    @Test
    public void createdWithFilledMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = new Post(new Message(MESSAGE_TEXT), new SocialTime(TIMESTAMP));

        String printedTimestamp = post.printTimestamp(new SocialTime(TIMESTAMP.plusMinutes(5)));

        assertThat(printedTimestamp, is(MESSAGE_TEXT + " (5 minutes ago)"));
    }

    @Test
    public void createdWithOtherMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = new Post(new Message(ANOTHER_MESSAGE_TEXT), new SocialTime(TIMESTAMP));

        String printedTimestamp = post.printTimestamp(new SocialTime(TIMESTAMP.plusMinutes(5)));

        assertThat(printedTimestamp, is(ANOTHER_MESSAGE_TEXT + " (5 minutes ago)"));
    }

}
