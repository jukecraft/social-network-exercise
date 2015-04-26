package timeline;

import java.util.Map;

public class WallOutput extends Output {

    @SuppressWarnings("unused")
    private Map<User, Output> timelines;

    public WallOutput(Map<User, Output> timelines) {
        this.timelines = timelines;
    }

}
