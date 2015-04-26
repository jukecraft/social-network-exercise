package commands;

import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {

    private final String name;

    public User(CommandParameter commandParameter) {
        this.name = commandParameter.getUser();
    }

    public int lengthOfName() {
        return name.length();
    }
}