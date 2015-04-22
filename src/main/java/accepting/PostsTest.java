package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class PostsTest {
    private static final Post A_POST = aPost() //
        .withPostingTime(now()) //
        .withMessage("message1") //
        .create();
    private static final Post LATER_POST = aPost() //
        .withPostingTime(now().plusYears(1)) //
        .withMessage("message2") //
        .create();
    private static final SocialTime PRINTING_TIME = aTime().create();

    private Posts posts = new Posts();

    @Test
    public void givenAnEmptyListOfPostsWhenThePostsArePrintedItReturnsNoOutput() {
        List<String> output = posts.printPosts(PRINTING_TIME);

        assertThat(output, is(empty()));
    }

    @Test
    public void givenAListOfOnePostWhenThePostsArePrintedItReturnsOutputWithThatPost() {
        posts.addPost(A_POST);

        List<String> output = posts.printPosts(PRINTING_TIME);

        assertThat(output, contains(A_POST.printTimestamp(PRINTING_TIME)));
    }

    @Test
    public void givenAListOfPostsWhenThePostsArePrintedItReturnsOutputWithThatPostsSortedLatestFirst() {
        posts.addPost(A_POST);
        posts.addPost(LATER_POST);

        List<String> output = posts.printPosts(PRINTING_TIME);

        assertThat(output, contains(LATER_POST.printTimestamp(PRINTING_TIME), A_POST.printTimestamp(PRINTING_TIME)));
    }
}
