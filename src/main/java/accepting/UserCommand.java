package accepting;

import commons.SocialNetworkingValueObject;

public class UserCommand extends SocialNetworkingValueObject {

    private final User user;

    public UserCommand(String command) {
        user = new User(command);
    }

    public User getUser() {
        return user;
    }

}
