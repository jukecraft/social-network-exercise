package commands;

import java.util.List;

import accepting.SocialTime;
import accepting.User;

public interface Command {
    boolean isApplicable(String command);

    List<String> executeCommand(User user, String command, SocialTime time);
}
