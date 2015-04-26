package timeline;

import commands.CommandParameter;
import commons.SocialNetworkingValueObject;

public class Message extends SocialNetworkingValueObject {
    private static final String MESSAGE_SEPARATOR = "-> ";

    private final String message;

    public Message(CommandParameter command) {
        this.message = command.afterSeparator(MESSAGE_SEPARATOR);
    }

    @Override
    public String toString() {
        return message;
    }

}
