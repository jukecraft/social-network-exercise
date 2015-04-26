package timeline;

import java.util.HashMap;
import java.util.Map;

public class WallOutput extends Output {

    private Map<User, Output> timelines = new HashMap<>();

    public void addPosts(User user, Output collectPosts) {
        timelines.put(user, collectPosts);
    }

}
