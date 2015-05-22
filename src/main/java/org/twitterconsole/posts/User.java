package org.twitterconsole.posts;

import org.twitterconsole.commons.SocialNetworkingValueObject;
import org.twitterconsole.io.Command;

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