package accepting;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

public class PostsTest {
    @Test
    public void givenAnEmptyListOfPostsItReturnsNoOutput() {
        Posts posts = new Posts();

        List<String> output = posts.printPosts();

        assertThat(output, is(empty()));
    }
}
