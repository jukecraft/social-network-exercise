package timeline;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.builder.SocialTimeBuilder.aTime;
import static timeline.builder.WallOutputBuilder.anEmptyWallOutput;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import time.SocialTime;

public class WallOutputTest {

    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();

    @Test
    public void givenNoPostsWhenAskedToPrintTimelineItPrintsNothing() {
        WallOutput output = anEmptyWallOutput().create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, is(empty()));
    }
}
