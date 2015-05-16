package posts.output;

import static posts.UserBuilder.aUser;
import static posts.output.PostsOutputBuilder.aPostsOutput;
import posts.User;

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
