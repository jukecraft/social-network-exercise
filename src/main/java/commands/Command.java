package commands;

import java.util.List;

import time.SocialTime;

public interface Command {
    boolean isApplicable(String command);

    List<String> executeCommand(User user, String command, SocialTime time);
}
