package accepting;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class PostingTimeTest {
    private static final LocalDateTime ORIGINAL_TIMESTAMP = now();
    private PostingTime originalPostingTime;

    @Before
    public void setUp() {
        originalPostingTime = new PostingTime(ORIGINAL_TIMESTAMP);
    }

    @Test
    public void createdWithTimestampItPrintsDifferenceToGivenTimestamp() {
        PostingTime timeOfPrinting = new PostingTime(ORIGINAL_TIMESTAMP.plusMinutes(5));

        String printedTimestamp = originalPostingTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("5 minutes ago"));
    }

    @Test
    public void createdWithDifferentTimestampItPrintsDifferenceToGivenTimestamp() {
        PostingTime timeOfPrinting = new PostingTime(ORIGINAL_TIMESTAMP.plusMinutes(10));

        String printedTimestamp = originalPostingTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("10 minutes ago"));
    }
}
