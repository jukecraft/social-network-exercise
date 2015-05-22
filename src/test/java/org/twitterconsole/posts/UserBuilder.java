package org.twitterconsole.posts;

import static org.twitterconsole.io.CommandBuilder.aCommand;

public class UserBuilder {
    private String name = "userName";

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public User create() {
        return new User(aCommand().withUser(name).create());
    }

    public static User aUserNamedAlice() {
        return aUser().withName("Alice").create();
    }

    public static User aUserNamedBob() {
        return aUser().withName("Bob").create();
    }
}
