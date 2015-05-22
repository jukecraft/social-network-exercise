package org.twitterconsole.posts;

import org.twitterconsole.commons.SocialNetworkingValueObject;
import org.twitterconsole.io.Command;

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
