package org.twitterconsole.network;

import java.util.HashMap;
import java.util.Map;

import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class SocialNetwork {
    private Map<User, Following> network = new HashMap<>();

    public void registerFollowing(User follower, User following) {
        Following followingForUser = getFollowing(follower);
        followingForUser.addFollowing(following);
        network.put(follower, followingForUser);
    }

    private Following getFollowing(User user) {
        return network.getOrDefault(user, new Following());
    }

    public WallOutput collectWallOutput(UsersPosts timelines, User wallUser) {
        return getFollowing(wallUser).collectWallOutput(timelines, wallUser);
    }

}
