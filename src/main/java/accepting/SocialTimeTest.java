package accepting;

import static accepting.SocialTimeBuilder.aTime;
import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class SocialTimeTest {
    private static final LocalDateTime ORIGINAL_TIMESTAMP = now();
    private SocialTime originalSocialTime;

    @Before
    public void setUp() {
        originalSocialTime = aTime() //
            .withTimestamp(ORIGINAL_TIMESTAMP) //
            .create();
    }

    @Test
    public void createdWithTimestampItPrintsDifferenceToGivenTimestamp() {
        SocialTime timeOfPrinting = aTime() //
            .withTimestamp(ORIGINAL_TIMESTAMP) //
            .plusMinutes(5) //
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("5 minutes ago"));
    }

    @Test
    public void createdWithDifferentTimestampItPrintsDifferenceToGivenTimestamp() {
        SocialTime timeOfPrinting = aTime() //
            .withTimestamp(ORIGINAL_TIMESTAMP) //
            .plusMinutes(10) //
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("10 minutes ago"));
    }
}
