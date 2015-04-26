package timeline;

import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import time.SocialTime;

public class WallOutput extends PostsOutput {

    private Map<User, PostsOutput> timelines = new HashMap<>();

    public void addPosts(User user, PostsOutput collectPosts) {
        timelines.put(user, collectPosts);
    }

    @Override
    public List<String> getOutput(SocialTime printingTime) {
        return emptyList();
    }

}
