package timeline;

import commands.User;

public class TimelineService {
    private final Timelines timelines;

    public TimelineService(Timelines timelines) {
        this.timelines = timelines;
    }

    public void post(User user, Post post) {
        timelines.post(user, post);
    }

    public Output collectPosts(User user) {
        return timelines.collectPosts(user);
    }

    public void registerFollowing(User follower, User follows) {
    }

}
