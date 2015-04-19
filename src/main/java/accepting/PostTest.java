package accepting;

import static accepting.Message.emptyMessage;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PostTest {
    @Test
    public void createdWithAnEmptyMessageItPrintsToEmpty() throws Exception {
        Post post = new Post(emptyMessage());

        assertThat(post.toString(), is(""));
    }
}
