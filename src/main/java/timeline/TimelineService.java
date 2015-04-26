package timeline;

import time.SocialTimeClock;

public class TimelineService {
    private final Timelines timelines;
    private final SocialTimeClock clock;

    public TimelineService(Timelines timelines, SocialNetwork network, SocialTimeClock clock) {
        this.timelines = timelines;
        this.clock = clock;
    }

    public Output collectPosts(User user) {
        return timelines.collectPosts(user);
    }

    public void registerFollowing(User follower, User follows) {
        follower.follows(follows);
    }

    public void post(User author, Message message) {
        timelines.post(author, new Post(message, clock.getLocalDateTime()));
    }

    public Output collectWall(User alice) {
        // TODO Auto-generated method stub
        return null;
    }
}
