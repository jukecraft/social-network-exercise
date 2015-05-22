package org.twitterconsole.network;

import java.util.ArrayList;
import java.util.List;

import org.twitterconsole.commons.SocialNetworkingValueObject;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class Following extends SocialNetworkingValueObject {
    private List<User> following = new ArrayList<>();

    public void addFollowing(User user) {
        following.add(user);
    }

    public WallOutput collectWallOutput(Timelines timelines, User wallUser) {
        ArrayList<User> relevantUsers = new ArrayList<>(following);
        relevantUsers.add(wallUser);
        return collectWalls(timelines, relevantUsers);
    }

    private WallOutput collectWalls(Timelines timelines, ArrayList<User> relevantUsers) {
        WallOutput wallOutput = new WallOutput();
        relevantUsers.forEach(user -> wallOutput.addPosts(user, timelines.collectPosts(user)));
        return wallOutput;
    }

}
