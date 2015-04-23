package accepting;

import java.util.HashMap;

public class Timelines {

    private HashMap<String, Timeline> timelines = new HashMap<>();

    public Timeline getPostsFor(String user) {
        return findTimeline(user);
    }

    public void post(String user, Post post) {
        Timeline timeline = findTimeline(user);
        timeline.addPost(post);
        timelines.put(user, timeline);
    }

    private Timeline findTimeline(String user) {
        return timelines.getOrDefault(user, new Timeline());
    }

}
