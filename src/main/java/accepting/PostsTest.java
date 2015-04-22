package accepting;

import static accepting.builder.PostBuilder.aPost;
import static accepting.builder.SocialTimeBuilder.aTime;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class PostsTest {
    private static final SocialTime PRINTING_TIME = aTime().create();
    private Posts posts = new Posts();

    @Test
    public void givenAnEmptyListOfPostsItReturnsNoOutput() {
        List<String> output = posts.printPosts(PRINTING_TIME);

        assertThat(output, is(empty()));
    }

    @Test
    public void givenAnEmptyListOfPostsWhenAPostIsAddedItReturnsOutputWithThatPost() {
        Post post = aPost().create();
        posts.addPost(post);

        List<String> output = posts.printPosts(PRINTING_TIME);

        assertThat(output, contains(post.printTimestamp(PRINTING_TIME)));
    }
}
