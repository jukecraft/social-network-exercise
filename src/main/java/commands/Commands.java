package commands;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

public class Commands {
    private List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public List<String> execute(String command, SocialTime time) {
        User user = new User(command);

        String commandWithoutUser = command.substring(user.lengthOfName());

        List<String> output = new ArrayList<>();
        for (Command command2 : commands) {
            if (command2.isApplicable(commandWithoutUser))
                output.addAll(command2.executeCommand(user, commandWithoutUser, time));
        }
        return output;
    }
}
