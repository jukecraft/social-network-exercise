package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;

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
