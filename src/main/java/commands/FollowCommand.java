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

    public void executeCommand(User alice, CommandParameter create, SocialTime time) {
        timelines.registerFollowing(alice, new User(new CommandParameter("Bob")));
    }

}
