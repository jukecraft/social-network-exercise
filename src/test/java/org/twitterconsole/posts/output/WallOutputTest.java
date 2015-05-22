package org.twitterconsole.posts.output;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.PostBuilder.aPost;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;
import static org.twitterconsole.posts.UserBuilder.aUserNamedBob;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;
import static org.twitterconsole.posts.output.WallOutputBuilder.anEmptyWallOutput;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;

public class WallOutputTest {

    private static final String SEPERATOR = " - ";
    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();

    private static final User ALICE = aUserNamedAlice();
    private static final String EARLIEST_MESSAGE = "I love the weather today";
    private static final String LATER_MESSAGE = "Good game though.";
    private static final Post ALICES_EARLIEST_POST = aPost()
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(5))
        .withMessage(EARLIEST_MESSAGE)
        .withUser(ALICE)
        .create();
    private static final Post ALICES_LATER_POST = aPost()
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(1))
        .withMessage(LATER_MESSAGE)
        .withUser(ALICE)
        .create();

    private static final User BOB = aUserNamedBob();
    private static final String EARLIER_MESSAGE = "Damn! We lost!";
    private static final String LATEST_MESSAGE = "I'm in New York today! Anyone want to have a coffee?";
    private static final Post BOBS_EARLIER_POST = aPost()
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(2))
        .withMessage(EARLIER_MESSAGE)
        .withUser(BOB)
        .create();
    private static final Post BOBS_LATEST_POST = aPost()
        .withPostingTime(PRINTING_TIMESTAMP.minusSeconds(15))
        .withMessage(LATEST_MESSAGE)
        .withUser(BOB)
        .create();

    @Test
    public void givenNoPostsWhenAskedToPrintItPrintsNothing() {
        WallOutput output = anEmptyWallOutput().create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, is(empty()));
    }

    @Test
    public void givenOneTimelinesWhenAskedToPrintItPrintsItWithTimestampsAndUsernameSortedLatestFirst() {
        PostsOutput alicesTimeline = anEmptyPostsOutput()
            .withPost(ALICES_EARLIEST_POST)
            .withPost(ALICES_LATER_POST)
            .create();

        WallOutput output = anEmptyWallOutput()
            .withTimeline(ALICE, alicesTimeline)
            .create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, contains(
            ALICE + SEPERATOR + LATER_MESSAGE + " (1 minute ago)",
            ALICE + SEPERATOR + EARLIEST_MESSAGE + " (5 minutes ago)"));
    }

    @Test
    public void givenTwoTimelinesWhenAskedToPrintItPrintsThemWithTimestampsAndUsernameSortedLatestFirst() {
        PostsOutput alicesTimeline = anEmptyPostsOutput()
            .withPost(ALICES_EARLIEST_POST)
            .withPost(ALICES_LATER_POST)
            .create();
        PostsOutput bobsTimeline = anEmptyPostsOutput()
            .withPost(BOBS_EARLIER_POST)
            .withPost(BOBS_LATEST_POST)
            .create();

        WallOutput output = anEmptyWallOutput()
            .withTimeline(ALICE, alicesTimeline)
            .withTimeline(BOB, bobsTimeline)
            .create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, contains(
            BOB + SEPERATOR + LATEST_MESSAGE + " (15 seconds ago)",
            ALICE + SEPERATOR + LATER_MESSAGE + " (1 minute ago)",
            BOB + SEPERATOR + EARLIER_MESSAGE + " (2 minutes ago)",
            ALICE + SEPERATOR + EARLIEST_MESSAGE + " (5 minutes ago)"));
    }
}
