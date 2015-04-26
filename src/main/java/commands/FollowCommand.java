package commands;

import time.SocialTime;
import timeline.Timelines;

public class FollowCommand {

    private Timelines timelines;

    public FollowCommand(Timelines timelines) {
        this.timelines = timelines;
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(" follows ");
    }

    public void executeCommand(User alice, CommandParameter parameter, SocialTime time) {
        User user = new User(new CommandParameter("Bob"));
        timelines.registerFollowing(alice, user);
    }

}
