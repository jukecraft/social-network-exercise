package accepting;

import commons.SocialNetworkingValueObject;

public class Command extends SocialNetworkingValueObject {
    private static final String COMMAND_SEPERATOR = " ";

    private final String user;
    private final Message message;

    public Command(String command) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        user = commandParts[0];
        message = new Message(command.substring(user.length()));
    }

    public String getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }

    public Post createPost(SocialTime time) {
        return new Post(message, time);
    }

}
