package timeline;

public class WallOutputBuilder {
    private WallOutput output = new WallOutput();

    public static WallOutputBuilder anEmptyWallOutput() {
        return new WallOutputBuilder();
    }

    public WallOutputBuilder withTimeline(User user, Output posts) {
        output.addPosts(user, posts);
        return this;
    }

    public WallOutput create() {
        return output;
    }

}
