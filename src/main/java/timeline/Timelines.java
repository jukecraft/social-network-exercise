package timeline;

import java.util.HashMap;

import time.SocialTime;

import commands.User;

public class Timelines {
    public HashMap<User, Timeline> timelines = new HashMap<User, Timeline>();

    public void post(User author, Post post) {
        Timeline timeline = get(author);
        timeline.addPost(post);
        timelines.put(author, timeline);
    }

    public Output printTimeline(User user, SocialTime time) {
        return get(user).printTimeline(time);
    }

    private Timeline get(User user) {
        return timelines.getOrDefault(user, new Timeline());
    }

}