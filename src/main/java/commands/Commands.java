package commands;

import java.util.List;

import time.SocialTime;
import timeline.Output;

public class Commands {
    private List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public Output execute(String command, SocialTime time) {
        User user = new User(command);
        String commandWithoutUser = command.substring(user.lengthOfName());
        return execute(time, user, commandWithoutUser);
    }

    private Output execute(SocialTime time, User user, String commandAsString) {
        return commands.stream() //
            .filter(candidate -> candidate.isApplicable(commandAsString)) //
            .findFirst() //
            .map(command -> command.executeCommand(user, commandAsString, time)) //
            .orElse(new Output());
    }
}
