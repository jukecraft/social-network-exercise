package accepting;

import java.util.HashMap;
import java.util.List;

public class Timelines {
    private HashMap<User, Timeline> timelines = new HashMap<>();

    public void post(User user, Post post) {
        Timeline timeline = findTimeline(user);
        timeline.addPost(post);
        timelines.put(user, timeline);
    }

    private Timeline findTimeline(User user) {
        return timelines.getOrDefault(user, new Timeline());
    }

    public List<String> printTimeline(User user, SocialTime time) {
        return findTimeline(user).printTimeline(time);
    }

}
