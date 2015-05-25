package org.twitterconsole.network;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;
import java.util.List;

import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class Following {
    private List<User> following = new ArrayList<>();

    public void addFollowing(User user) {
        following.add(user);
    }

    public WallOutput collectWallOutput(UsersPosts timelines, User wallUser) {
        ArrayList<User> relevantUsers = new ArrayList<>(following);
        relevantUsers.add(wallUser);
        return collectWalls(timelines, relevantUsers);
    }

    private WallOutput collectWalls(UsersPosts timelines, ArrayList<User> relevantUsers) {
        WallOutput wallOutput = new WallOutput();
        relevantUsers.forEach(user -> wallOutput.addPosts(user, timelines.collectPosts(user)));
        return wallOutput;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

}
