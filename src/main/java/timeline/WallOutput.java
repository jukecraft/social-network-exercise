package timeline;

import java.util.HashMap;
import java.util.Map;

public class WallOutput extends PostsOutput {

    private Map<User, PostsOutput> timelines = new HashMap<>();

    public void addPosts(User user, PostsOutput collectPosts) {
        timelines.put(user, collectPosts);
    }

}
