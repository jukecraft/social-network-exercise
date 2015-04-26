package timeline;

import java.util.ArrayList;
import java.util.List;

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

    public PostsOutput collectPosts(User user) {
        return timelines.collectPosts(user);
    }

    public void registerFollowing(User follower, User follows) {
        network.registerFollowing(follower, follows);
    }

    public void post(User author, Message message) {
        timelines.post(author, new Post(message, clock.getLocalDateTime()));
    }

    public PostsOutput collectWall(User wallUser) {
        List<User> relevantUsers = new ArrayList<User>(network.getFollowing(wallUser));
        relevantUsers.add(wallUser);
        return collectWallOutput(relevantUsers);
    }

    private PostsOutput collectWallOutput(List<User> relevantUsers) {
        WallOutput wallOutput = new WallOutput();
        relevantUsers.forEach(user -> wallOutput.addPosts(user, collectPosts(user)));
        return wallOutput;
    }
}
