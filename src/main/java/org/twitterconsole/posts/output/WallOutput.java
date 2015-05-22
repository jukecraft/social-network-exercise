package org.twitterconsole.posts.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.twitterconsole.commons.SocialNetworkingValueObject;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;

public class WallOutput extends SocialNetworkingValueObject implements Output {

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

}
