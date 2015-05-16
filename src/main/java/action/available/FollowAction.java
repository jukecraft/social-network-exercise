package action.available;

import io.Command;
import network.TimelineService;
import posts.User;
import action.Action;

public class FollowAction implements Action {
    private static final String FOLLOW_IDENTIFIER = " follows ";

    private TimelineService timelineService;

    public FollowAction(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isExecutable(Command command) {
        return command.startsWith(FOLLOW_IDENTIFIER);
    }

    @Override
    public void execute(Command command) {
        timelineService.registerFollowing(new User(command), extractSecondUser(command));
    }

    private User extractSecondUser(Command command) {
        Command secondUserParameter = new Command(command.afterIdentifier(FOLLOW_IDENTIFIER));
        return new User(secondUserParameter);
    }

}
