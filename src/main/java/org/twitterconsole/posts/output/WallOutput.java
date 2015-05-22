package org.twitterconsole.posts.output;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;

public class WallOutput implements Output {

    private Map<User, PostsOutput> timelines = new HashMap<>();

    public void addPosts(User user, PostsOutput posts) {
        timelines.put(user, posts);
    }

    @Override
    public List<String> print(SocialTime printingTime) {
        PostsOutput mergedPosts = timelines.values().stream()
            .reduce((output1, output2) -> output1.mergeWith(output2))
            .orElse(new PostsOutput());
        return mergedPosts.printWithUser(printingTime);
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
