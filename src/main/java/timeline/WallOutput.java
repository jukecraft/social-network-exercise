package timeline;

import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import time.SocialTime;

import commons.SocialNetworkingValueObject;

public class WallOutput extends SocialNetworkingValueObject implements Output {

    private Map<User, PostsOutput> timelines = new HashMap<>();

    public void addPosts(User user, PostsOutput collectPosts) {
        timelines.put(user, collectPosts);
    }

    @Override
    public List<String> print(SocialTime printingTime) {
        return emptyList();
    }

}
