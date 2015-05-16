package action.available;

import io.Command;
import network.TimelineService;
import posts.Message;
import posts.User;
import time.SocialNetworkingClock;
import action.Action;

public class PostAction implements Action {
    private static final String POST_IDENTIFIER = " -> ";

    private TimelineService timelineService;
    private SocialNetworkingClock clock;

    public PostAction(TimelineService timelineService, SocialNetworkingClock clock) {
        this.timelineService = timelineService;
        this.clock = clock;
    }

    @Override
    public boolean isExecutable(Command command) {
        return command.startsWith(POST_IDENTIFIER);
    }

    @Override
    public void execute(Command command) {
        timelineService.post(new User(command), new Message(command), clock.getLocalDateTime());
    }
}
