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

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import time.SocialTime;

public class PostsOutputTest {
    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();
    private static final Post A_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(5)) //
        .withMessage("earlier message") //
        .withUser(aUser().withName("Alice").create()) //
        .create();

    private static final Post LATER_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(1)) //
        .withMessage("later message") //
        .withUser(aUser().withName("Bob").create()) //
        .create();

    @Test
    public void givenNoPostsWhenAskedToPrintTimelineItPrintsNothing() {
        Output output = anEmptyPostsOutput().create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, is(empty()));
    }

    @Test
    public void givenTwoPostsWhenAskedToPrintTimelineItPrintsThemWithTimestampsSortedLatestFirst() {
        Output output = anEmptyPostsOutput() //
            .withPost(A_POST) //
            .withPost(LATER_POST) //
            .create();

        List<String> printedTimeline = output.print(PRINTING_TIME);

        assertThat(printedTimeline, contains(LATER_POST.printAt(PRINTING_TIME), A_POST.printAt(PRINTING_TIME)));
    }

    @Test
    public void givenTwoPostsWhenAskedToPrintWithUserItPrintsThemWithTimestampsAndUsersSortedLatestFirst() {
        PostsOutput output = anEmptyPostsOutput() //
            .withPost(A_POST) //
            .withPost(LATER_POST) //
            .create();

        List<String> printedTimeline = output.printWithUser(PRINTING_TIME);

        assertThat(printedTimeline,
            contains(LATER_POST.printWithUser(PRINTING_TIME), A_POST.printWithUser(PRINTING_TIME)));
    }

    @Test
    public void givenAnotherPostOutputItCanMergeThePosts() {
        PostsOutput output = anEmptyPostsOutput() //
            .withPost(A_POST) //
            .create();
        PostsOutput anotherOutput = anEmptyPostsOutput() //
            .withPost(LATER_POST) //
            .create();

        Output merged = output.mergeWith(anotherOutput);

        assertThat(merged, is(anEmptyPostsOutput().withPost(A_POST).withPost(LATER_POST).create()));
    }
}
