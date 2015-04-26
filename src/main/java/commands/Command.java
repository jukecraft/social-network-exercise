package commands;

import time.SocialTime;
import timeline.Output;

public interface Command {
    boolean isApplicable(CommandParameter commandParameter);

    Output executeCommand(User user, CommandParameter commandParameter, SocialTime time);
}
