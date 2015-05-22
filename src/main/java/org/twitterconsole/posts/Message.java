package org.twitterconsole.posts;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

import org.twitterconsole.io.Command;

public class Message {
    private static final String MESSAGE_SEPARATOR = "-> ";

    private final String message;

    public Message(Command command) {
        this.message = command.afterIdentifier(MESSAGE_SEPARATOR);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

}
