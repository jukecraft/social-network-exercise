package timeline.builder;

import timeline.PostsOutput;
import timeline.User;
import timeline.WallOutput;

public class WallOutputBuilder {
    private WallOutput output = new WallOutput();

    public static WallOutputBuilder anEmptyWallOutput() {
        return new WallOutputBuilder();
    }

    public WallOutputBuilder withTimeline(User user, PostsOutput posts) {
        output.addPosts(user, posts);
        return this;
    }

    public WallOutput create() {
        return output;
    }

}
