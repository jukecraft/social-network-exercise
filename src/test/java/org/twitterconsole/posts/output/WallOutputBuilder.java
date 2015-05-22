package org.twitterconsole.posts.output;

import static org.twitterconsole.posts.UserBuilder.aUser;
import static org.twitterconsole.posts.output.PostsOutputBuilder.aPostsOutput;

import org.twitterconsole.posts.User;

public class WallOutputBuilder {
    private WallOutput output = new WallOutput();

    public static WallOutputBuilder anEmptyWallOutput() {
        return new WallOutputBuilder();
    }

    public static WallOutputBuilder aWallOutput() {
        return new WallOutputBuilder() //
            .withTimeline(aUser().create(), aPostsOutput() //
                .create());
    }

    public WallOutputBuilder withTimeline(User user, PostsOutput posts) {
        output.addPosts(user, posts);
        return this;
    }

    public WallOutput create() {
        return output;
    }

}
