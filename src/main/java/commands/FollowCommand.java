package commands;

import time.SocialTime;
import timeline.Output;
import timeline.TimelineService;

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
        CommandParameter secondUserParameter = new CommandParameter(parameter.afterSeparator(COMMAND_IDENTIFIER));
        User user = new User(secondUserParameter);
        timelines.registerFollowing(alice, user);
        return new Output();
    }

}
