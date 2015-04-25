package accepting;

import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {
    @SuppressWarnings("unused")
    private final String name;

    public User(String name) {
        this.name = name;
    }
}