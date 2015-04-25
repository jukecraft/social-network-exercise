package accepting;

import commons.SocialNetworkingValueObject;

public class Message extends SocialNetworkingValueObject {
    private static final String MESSAGE_SEPARATOR = "-> ";

    private final String message;

    public Message(String message) {
        String[] messageParts = message.split(MESSAGE_SEPARATOR);
        this.message = messageParts[1];
    }

    @Override
    public String toString() {
        return message;
    }

}
