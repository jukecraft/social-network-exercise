package commands;

import time.SocialTime;
import timeline.Output;
import timeline.TimelineService;
import timeline.User;

public class FollowCommand {
    private static final String COMMAND_IDENTIFIER = " follows ";

    private TimelineService timelines;

    public FollowCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(COMMAND_IDENTIFIER);
    }

    public Output executeCommand(User alice, CommandParameter parameter, SocialTime time) {
        User user = extractUser(parameter);
        timelines.registerFollowing(alice, user);
        return new Output();
    }

    private User extractUser(CommandParameter parameter) {
        CommandParameter secondUserParameter = new CommandParameter(parameter.afterIdentifier(COMMAND_IDENTIFIER));
        return new User(secondUserParameter);
    }

}
