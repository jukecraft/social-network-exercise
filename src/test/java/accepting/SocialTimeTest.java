package accepting;

import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class SocialTimeTest {
    private SocialTime originalSocialTime;

    @Before
    public void setUp() {
        originalSocialTime = aTime() //
            .create();
    }

    @Test
    public void createdWithTimestampItPrintsDifferenceToGivenTimestamp() {
        SocialTime timeOfPrinting = aTime() //
            .plusMinutes(5) //
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("5 minutes ago"));
    }

    @Test
    public void createdWithDifferentTimestampItPrintsDifferenceToGivenTimestamp() {
        SocialTime timeOfPrinting = aTime() //
            .plusMinutes(10) //
            .create();

        String printedTimestamp = originalSocialTime.printTimestamp(timeOfPrinting);

        assertThat(printedTimestamp, is("10 minutes ago"));
    }
}
