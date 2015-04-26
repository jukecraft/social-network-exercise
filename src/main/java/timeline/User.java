package timeline;

import commands.CommandParameter;
import commons.SocialNetworkingValueObject;

public class User extends SocialNetworkingValueObject {

    private final String name;

    public User(CommandParameter commandParameter) {
        this.name = commandParameter.getUser();
    }

    public void follows(User follows) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return name;
    }

}