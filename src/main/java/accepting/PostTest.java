package accepting;

import static accepting.Message.emptyMessage;
import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostTest {
    private static final PostingTime IRRELLEVANT_POSTING_TIME = null;
    private static final Message IRELLEVANT_MESSAGE = null;

    @Test
    public void createdWithAnEmptyMessageItPrintsToEmpty() {
        Post post = new Post(emptyMessage(), IRRELLEVANT_POSTING_TIME);

        assertThat(post.toString(), is(""));
    }

    @Test
    public void createdWithFilledMessageItPrintsThatMessage() {
        Message message = new Message("I love the weather today");
        Post post = new Post(message, IRRELLEVANT_POSTING_TIME);

        assertThat(post.toString(), is("I love the weather today"));
    }

    @Test
    public void createdWithPostingTimeItPrintsTheTimePassedSinceThen() {
        PostingTime postingTime = new PostingTime(now());
        Post post = new Post(IRELLEVANT_MESSAGE, postingTime);

        String printedTimestamp = post.printTimestamp(postingTime.plusMinutes(5));

        assertThat(printedTimestamp, is(" (5 minutes ago)"));
    }

}
