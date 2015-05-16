package posts.output;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static posts.PostBuilder.aPost;
import static posts.PostBuilder.aUserNamedAlice;
import static posts.PostBuilder.aUserNamedBob;
import static posts.SocialTimeBuilder.aTime;
import static posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import posts.Post;
import posts.SocialTime;
import posts.UserBuilder;
import posts.UserBuilder.aUserNamedAlice;
import posts.UserBuilder.aUserNamedBob;

public class PostsOutputTest {
    private static final LocalDateTime PRINTING_TIMESTAMP = now();
    private static final SocialTime PRINTING_TIME = aTime().withTimestamp(PRINTING_TIMESTAMP).create();
    private static final Post A_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(5)) //
        .withMessage("earlier message") //
        .withUser(UserBuilder.aUserNamedAlice()) //
        .create();

    private static final Post LATER_POST = aPost() //
        .withPostingTime(PRINTING_TIMESTAMP.minusMinutes(1)) //
        .withMessage("later message") //
        .withUser(UserBuilder.aUserNamedBob()) //
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
