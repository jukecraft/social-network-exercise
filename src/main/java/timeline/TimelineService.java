package timeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import time.SocialTimeClock;

public class TimelineService {
    private final Timelines timelines;
    private final SocialTimeClock clock;
    private SocialNetwork network;

    public TimelineService(Timelines timelines, SocialNetwork network, SocialTimeClock clock) {
        this.timelines = timelines;
        this.network = network;
        this.clock = clock;
    }

    public Output collectPosts(User user) {
        return timelines.collectPosts(user);
    }

    public void registerFollowing(User follower, User follows) {
        network.registerFollowing(follower, follows);
    }

    public void post(User author, Message message) {
        timelines.post(author, new Post(message, clock.getLocalDateTime()));
    }

    public Output collectWall(User alice) {
        List<User> following = network.getFollowing(alice);

        Map<User, Output> relevantPosts = new HashMap<>();

        following.forEach(user -> relevantPosts.put(user, collectPosts(user)));
        relevantPosts.put(alice, collectPosts(alice));

        return new WallOutput(relevantPosts);
    }
}
