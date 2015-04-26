package timeline;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.builder.PostBuilder.aPost;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;
import static timeline.builder.SocialTimeBuilder.aTime;
import static timeline.builder.UserBuilder.aUser;
import static timeline.builder.WallOutputBuilder.anEmptyWallOutput;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import time.SocialTime;

public class WallOutputTest {

    private static final String SEPERATOR = " - ";
    private static final String ALICE_USERNAME = "Alice";
    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();

    private static final String EARLIEST_MESSAGE = "I love the weather today";
    private static final String LATER_MESSAGE = "Good game though.";

    private static final Post ALICES_FIRST_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(5)) //
        .withMessage(EARLIEST_MESSAGE) //
        .create();
    private static final Post ALICES_SECOND_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(1)) //
        .withMessage(LATER_MESSAGE) //
        .create();

    private static final User ALICE = aUser().withName(ALICE_USERNAME).create();

    @Test
    public void givenNoPostsWhenAskedToPrintItPrintsNothing() {
        WallOutput output = anEmptyWallOutput().create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, is(empty()));
    }

    @Test
    public void givenOneTimelinesWhenAskedToPrintItPrintsItWithTimestampsAndUsernameSortedLatestFirst() {
        PostsOutput alicesTimeline = anEmptyPostsOutput() //
            .withPost(ALICES_FIRST_POST) //
            .withPost(ALICES_SECOND_POST) //
            .create();

        WallOutput output = anEmptyWallOutput() //
            .withTimeline(ALICE, alicesTimeline) //
            .create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, contains( //
            ALICE_USERNAME + SEPERATOR + LATER_MESSAGE + " (1 minute ago)", //
            ALICE_USERNAME + SEPERATOR + EARLIEST_MESSAGE + " (5 minutes ago)"));
    }
}
