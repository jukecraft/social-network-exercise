package posts;

import io.Command;

import commons.SocialNetworkingValueObject;

public class Message extends SocialNetworkingValueObject {
    private static final String MESSAGE_SEPARATOR = "-> ";

    private final String message;

    public Message(Command command) {
        this.message = command.afterIdentifier(MESSAGE_SEPARATOR);
    }

    @Override
    public String toString() {
        return message;
    }

}
