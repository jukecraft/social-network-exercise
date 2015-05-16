package network;

import posts.Message;
import posts.Post;
import posts.SocialTime;
import posts.User;
import posts.output.PostsOutput;
import posts.output.WallOutput;

public class TimelineService {
    private final Timelines timelines;
    private final SocialNetwork network;

    public TimelineService(Timelines timelines, SocialNetwork network) {
        this.timelines = timelines;
        this.network = network;
    }

    public PostsOutput collectPosts(User user) {
        return timelines.collectPosts(user);
    }

    public void registerFollowing(User follower, User follows) {
        network.registerFollowing(follower, follows);
    }

    public void post(User author, Message message, SocialTime timestamp) {
        timelines.post(author, new Post(author, message, timestamp));
    }

    public WallOutput collectWall(User wallUser) {
        return network.collectWallOutput(timelines, wallUser);
    }

}
