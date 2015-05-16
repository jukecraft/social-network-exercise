package network;

import java.util.HashMap;
import java.util.Map;

import posts.User;
import posts.output.WallOutput;

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

    public WallOutput collectWallOutput(Timelines timelines, User wallUser) {
        return getFollowing(wallUser).collectWallOutput(timelines, wallUser);
    }

}
