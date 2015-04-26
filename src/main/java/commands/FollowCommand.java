package commands;

import time.SocialTime;
import timeline.Timelines;

public class FollowCommand {

    public FollowCommand(Timelines timelines) {
        // TODO Auto-generated constructor stub
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(" follows ");
    }

    public void executeCommand(User alice, CommandParameter create, SocialTime time) {
        // TODO Auto-generated method stub

    }

}
