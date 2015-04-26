package timeline;

import java.util.HashMap;

public class Timelines {
    public HashMap<User, Timeline> timelines = new HashMap<User, Timeline>();

    public void post(User author, Post post) {
        Timeline timeline = get(author);
        timeline.addPost(post);
        timelines.put(author, timeline);
    }

    public Output collectPosts(User user) {
        return get(user).collectPosts();
    }

    private Timeline get(User user) {
        return timelines.getOrDefault(user, new Timeline());
    }

}