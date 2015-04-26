package timeline;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;

public class TimelineTest {
    private static final Post A_POST = aPost() //
        .withPostingTime(now()) //
        .withMessage("message1") //
        .create();
    private static final Post LATER_POST = aPost() //
        .withPostingTime(now().plusYears(1)) //
        .withMessage("message2") //
        .create();
    private static final SocialTime PRINTING_TIME = aTime().create();

    private Timeline timeline = new Timeline();

    @Test
    public void givenAnEmptyListOfPostsWhenThePostsArePrintedItReturnsNoOutput() {
        Output output = timeline.collectPosts();

        assertThat(output, is(anEmptyOutput().create()));
    }

    @Test
    public void givenAListOfOnePostWhenThePostsArePrintedItReturnsOutputWithThatPost() {
        timeline.addPost(A_POST);

        Output output = timeline.collectPosts();

        assertThat(output, is(anEmptyOutput() //
            .withPost(A_POST) //
            .create()));
    }

    @Test
    public void givenAListOfPostsWhenThePostsArePrintedItReturnsOutputWithBothPosts() {
        timeline.addPost(A_POST);
        timeline.addPost(LATER_POST);

        Output output = timeline.collectPosts();

        assertThat(output, is(anEmptyOutput() //
            .withPost(A_POST) //
            .withPost(LATER_POST) //
            .create()));
    }
}
