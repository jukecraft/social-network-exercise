package accepting;

import java.util.HashMap;

public class Timelines {
    private HashMap<User, Timeline> timelines = new HashMap<>();

    public Timeline getPostsFor(User user) {
        return findTimeline(user);
    }

    public void post(User user, Post post) {
        Timeline timeline = findTimeline(user);
        timeline.addPost(post);
        timelines.put(user, timeline);
    }

    private Timeline findTimeline(User user) {
        return timelines.getOrDefault(user, new Timeline());
    }

}
