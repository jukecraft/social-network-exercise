package accepting;

import java.util.HashMap;

public class Timelines {

    private HashMap<String, Timeline> timelines = new HashMap<>();

    public Timeline getPostsFor(String user) {
        return timelines.getOrDefault(user, new Timeline());
    }

    public void post(String user, Post post) {
        Timeline timeline = timelines.getOrDefault(user, new Timeline());
        timeline.addPost(post);
        timelines.put(user, timeline);
    }

}
