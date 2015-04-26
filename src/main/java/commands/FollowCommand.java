package commands;

import time.SocialTime;
import timeline.Timelines;

public class FollowCommand {

    private static final String COMMAND_IDENTIFIER = " follows ";
    private Timelines timelines;

    public FollowCommand(Timelines timelines) {
        this.timelines = timelines;
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(COMMAND_IDENTIFIER);
    }

    public void executeCommand(User alice, CommandParameter parameter, SocialTime time) {
        CommandParameter secondUserParameter = new CommandParameter(parameter.afterSeparator(COMMAND_IDENTIFIER));
        User user = new User(secondUserParameter);
        timelines.registerFollowing(alice, user);
    }

}
