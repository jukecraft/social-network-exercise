package timeline;

import io.CommandParameter;

import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {

    private final String name;

    public User(CommandParameter commandParameter) {
        this.name = commandParameter.getUser();
    }

    @Override
    public String toString() {
        return name;
    }

}