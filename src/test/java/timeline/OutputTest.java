package timeline;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import time.SocialTime;

public class OutputTest {
    private static final String EARLIER_MESSAGE = "earlier message";
    private static final String LATER_MESSAGE = "later message";
    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();
    private static final Post A_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(5)) //
        .withMessage(EARLIER_MESSAGE) //
        .create();
    private static final Post LATER_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(1)) //
        .withMessage(LATER_MESSAGE) //
        .create();

    @Test
    public void givenNoPostsWhenAskedToPrintTimelineItPrintsNothing() {
        Output output = new Output();

        List<String> printedTimeline = output.getOutput(PRINTING_TIME);

        assertThat(printedTimeline, is(empty()));
    }

    @Test
    public void givenTwoPostsWhenAskedToPrintTimelineItPrintsThemWithTimestampsSortedLatestFirst() {
        Output output = new Output(asList(A_POST, LATER_POST));

        List<String> printedTimeline = output.getOutput(PRINTING_TIME);

        assertThat(printedTimeline, contains(LATER_MESSAGE + " (1 minute ago)", EARLIER_MESSAGE + " (5 minutes ago)"));
    }
}
