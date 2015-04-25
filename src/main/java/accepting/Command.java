package accepting;

import commons.SocialNetworkingValueObject;

public class Command extends SocialNetworkingValueObject {
    private static final String COMMAND_SEPERATOR = " ";

    private final User user;
    private final Message message;

    public Command(String command) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        user = new User(commandParts[0]);
        message = new Message(command.substring(commandParts[0].length()));
    }

    public User getUser() {
        return user;
    }

    public Post createPost(SocialTime time) {
        return new Post(message, time);
    }

}
