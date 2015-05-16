package posts;

import io.Command;

import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {

    private final String name;

    public User(Command command) {
        this.name = command.getUser();
    }

    @Override
    public String toString() {
        return name;
    }

}