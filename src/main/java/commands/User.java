package commands;

import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {
    private static final String COMMAND_SEPERATOR = " ";

    private final String name;

    public User(String command) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        this.name = commandParts[0];
    }

    public int lengthOfName() {
        return name.length();
    }
}