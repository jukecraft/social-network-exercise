package timeline;

import java.util.HashMap;
import java.util.Map;

public class WallOutputBuilder {
    private Map<User, Output> timelines = new HashMap<>();

    public static WallOutputBuilder aWallOutput() {
        return new WallOutputBuilder();
    }

    public WallOutputBuilder withTimeline(User user, Output timeline) {
        timelines.put(user, timeline);
        return this;
    }

    public WallOutput create() {
        return new WallOutput(timelines);
    }

}
