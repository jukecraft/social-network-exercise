package network;

import java.util.HashMap;

import posts.Post;
import posts.User;
import posts.output.PostsOutput;

public class Timelines {
    public HashMap<User, Timeline> timelines = new HashMap<User, Timeline>();

    public void post(User author, Post post) {
        Timeline timeline = get(author);
        timeline.addPost(post);
        timelines.put(author, timeline);
    }

    public PostsOutput collectPosts(User user) {
        return get(user).collectPosts();
    }

    private Timeline get(User user) {
        return timelines.getOrDefault(user, new Timeline());
    }

}