package timeline;

import java.util.HashMap;

import time.SocialTime;

import commands.User;

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

    public Output printTimeline(User user, SocialTime time) {
        return findTimeline(user).printTimeline(time);
    }

}
