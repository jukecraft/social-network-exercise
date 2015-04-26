package timeline;

import time.SocialTime;

import commands.User;

public class TimelineService {
    private final Timelines timelines;

    public TimelineService(Timelines timelines) {
        this.timelines = timelines;
    }

    public void post(User user, Post post) {
        timelines.post(user, post);
    }

    public Output printTimeline(User user, SocialTime time) {
        return timelines.printTimeline(user, time);
    }

    public void registerFollowing(User follower, User follows) {
    }

}
