package commands;

import commons.SocialNetworkingValueObject;

public class CommandParameter extends SocialNetworkingValueObject {
    private static final String COMMAND_SEPERATOR = " ";

    private final String[] commandParts;
    private String commandWithoutUser;

    public CommandParameter(String command) {
        commandParts = command.split(COMMAND_SEPERATOR);
        commandWithoutUser = command.substring(getUser().length());
    }

    public String getUser() {
        return commandParts[0];
    }

    public boolean startsWith(String identifier) {
        return commandWithoutUser.startsWith(identifier);
    }

    public String afterIdentifier(String identifier) {
        String[] commandParts = commandWithoutUser.split(identifier);
        return commandParts[1];
    }

    public boolean isEmpty() {
        return commandWithoutUser.isEmpty();
    }
}