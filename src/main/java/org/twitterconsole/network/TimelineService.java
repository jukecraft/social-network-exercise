package org.twitterconsole.network;

import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class TimelineService {
    private final UsersPosts usersPosts;
    private final SocialNetwork network;

    public TimelineService(UsersPosts usersPosts, SocialNetwork network) {
        this.usersPosts = usersPosts;
        this.network = network;
    }

    public void registerFollowing(User follower, User follows) {
        network.registerFollowing(follower, follows);
    }

    public WallOutput collectWall(User wallUser) {
        return network.collectWallOutput(usersPosts, wallUser);
    }

    public void post(User author, Post post) {
        usersPosts.post(post);
    }

}
