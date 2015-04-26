package timeline;

import java.util.ArrayList;
import java.util.List;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public PostsOutput collectPosts() {
        return new PostsOutput(posts);
    }

    public void addPost(Post create) {
        posts.add(create);
    }

}
