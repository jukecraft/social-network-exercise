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

        Output output = new Output();
        for (Command command2 : commands) {
            if (command2.isApplicable(commandWithoutUser))
                output.add(command2.executeCommand(user, commandWithoutUser, time));
        }
        return output;
    }
}
