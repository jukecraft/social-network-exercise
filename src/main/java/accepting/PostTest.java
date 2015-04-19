package accepting;

import static accepting.Message.emptyMessage;
import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostTest {
    @Test
    public void createdWithAnEmptyMessageItPrintsToEmpty() {
        Post post = new Post(emptyMessage());

        assertThat(post.toString(), is(""));
    }

    @Test
    public void createdWithFilledMessageItPrintsThatMessage() {
        Message message = new Message("I love the weather today");
        Post post = new Post(message);

        assertThat(post.toString(), is("I love the weather today"));
    }

    @Test
    public void createdWithPostingTimeItPrintsTheTimePassedSinceThen() {
        Message message = new Message("I love the weather today");
        PostingTime postingTime = new PostingTime(now());
        Post post = new Post(message, postingTime);

        String printedTimestamp = post.printTimestamp(postingTime.plusMinutes(5));

        assertThat(printedTimestamp, is(" (5 minutes ago)"));
    }

}
