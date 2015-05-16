package io;

import commons.SocialNetworkingValueObject;

public class Command extends SocialNetworkingValueObject {
    public static final Command NOTHING = new Command();

    private static final String COMMAND_SEPERATOR = " ";

    private String[] commandParts;
    private String commandWithoutUser;

    private Command() {
    }

    public Command(String command) {
        commandParts = command.split(COMMAND_SEPERATOR);
        commandWithoutUser = command.substring(getUser().length());
    }

    public String getUser() {
        return commandParts[0];
    }

    public boolean isEmpty() {
        return commandParts[0].length() == 0;
    }

    public boolean startsWith(String identifier) {
        return commandWithoutUser.startsWith(identifier);
    }

    public String afterIdentifier(String identifier) {
        String[] commandParts = commandWithoutUser.split(identifier);
        return commandParts[1];
    }

    public boolean isEmptyWithoutUser() {
        return commandWithoutUser.isEmpty();
    }
}