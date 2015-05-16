package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import posts.User;

public class SocialNetwork {
    private Map<User, List<User>> network = new HashMap<>();

    public void registerFollowing(User follower, User following) {
        List<User> followingForUser = getFollowing(follower);
        followingForUser.add(following);
        network.put(follower, followingForUser);
    }

    public List<User> getFollowing(User user) {
        return network.getOrDefault(user, new ArrayList<>());
    }

}
