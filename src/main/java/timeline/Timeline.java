package timeline;

import java.util.ArrayList;
import java.util.List;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public Output collectPosts() {
        return new Output(posts);
    }

    public void addPost(Post create) {
        posts.add(create);
    }

}
