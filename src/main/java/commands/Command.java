package commands;

import time.SocialTime;
import timeline.Output;

public interface Command {
    boolean isApplicable(String command);

    Output executeCommand(User user, String command, SocialTime time);
}
