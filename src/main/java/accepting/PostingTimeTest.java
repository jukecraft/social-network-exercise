package accepting;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostingTimeTest {
    @Test
    public void createdWithTimestampItPrintsDifferenceToGivenTimestamp() {
        PostingTime postingTime = new PostingTime(now());

        String timestamp = postingTime.printTimestamp(postingTime.plusMinutes(5));

        assertThat(timestamp, is("5 minutes ago"));
    }
}
