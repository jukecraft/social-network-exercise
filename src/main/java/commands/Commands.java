package commands;

import static java.util.Collections.emptyList;

import java.util.List;

import accepting.SocialTime;
import accepting.User;

public class Commands {
    private List<Command> commands;
    private static final String COMMAND_SEPERATOR = " ";

    public Commands(List<Command> commands) {

        this.commands = commands;
    }

    public List<String> execute(String command, SocialTime time) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        User user = new User(commandParts[0]);
        String commandWithoutUser = command.substring(commandParts[0].length());

        for (Command command2 : commands) {
            if (command2.isApplicable(commandWithoutUser))
                command2.executeCommand(user, commandWithoutUser, time);
        }
        return emptyList();
    }
}
