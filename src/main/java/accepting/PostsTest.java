package accepting;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import accepting.builder.PostBuilder;
import accepting.builder.SocialTimeBuilder;

public class PostsTest {
    @Test
    public void givenAnEmptyListOfPostsItReturnsNoOutput() {
        Posts posts = new Posts();

        List<String> output = posts.printPosts(SocialTimeBuilder.aTime().create());

        assertThat(output, is(empty()));
    }

    @Test
    public void givenAnEmptyListOfPostsWhenAPostIsAddedItReturnsOutputWithThatPost() {
        Posts posts = new Posts();
        Post post = PostBuilder.aPost().create();
        posts.addPost(post);
        SocialTime printingTime = SocialTimeBuilder.aTime().create();

        List<String> output = posts.printPosts(printingTime);

        assertThat(output, contains(post.printTimestamp(printingTime)));
    }
}
