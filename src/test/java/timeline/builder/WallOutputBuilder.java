package timeline.builder;

import static timeline.builder.PostsOutputBuilder.aPostsOutput;
import static timeline.builder.UserBuilder.aUser;
import timeline.PostsOutput;
import timeline.User;
import timeline.WallOutput;

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
