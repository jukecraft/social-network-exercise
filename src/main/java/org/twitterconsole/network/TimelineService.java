package org.twitterconsole.network;

import org.twitterconsole.posts.Message;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;
import org.twitterconsole.posts.output.WallOutput;

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
