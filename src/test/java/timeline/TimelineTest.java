package timeline;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;
import static timeline.builder.PostBuilder.aPost;

import org.junit.Test;

public class TimelineTest {
    private static final Post A_POST = aPost() //
        .withPostingTime(now()) //
        .withMessage("message1") //
        .create();
    private static final Post LATER_POST = aPost() //
        .withPostingTime(now().plusYears(1)) //
        .withMessage("message2") //
        .create();

    private Timeline timeline = new Timeline();

    @Test
    public void givenAnEmptyListOfPostsWhenThePostsArePrintedItReturnsNoOutput() {
        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void givenAListOfOnePostWhenThePostsArePrintedItReturnsOutputWithThatPost() {
        timeline.addPost(A_POST);

        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .create()));
    }

    @Test
    public void givenAListOfPostsWhenThePostsArePrintedItReturnsOutputWithBothPosts() {
        timeline.addPost(A_POST);
        timeline.addPost(LATER_POST);

        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .withPost(LATER_POST) //
            .create()));
    }
}
