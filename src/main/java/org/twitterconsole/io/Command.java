package org.twitterconsole.io;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Command {
    public static final Command NOTHING = new Command();

    private static final String COMMAND_SEPERATOR = " ";

    private String[] commandParts;
    private String commandAfterFirstParameter;

    private Command() {
    }

    public Command(String command) {
        commandParts = command.split(COMMAND_SEPERATOR);
        commandAfterFirstParameter = command.substring(getFirstParameter().length());
    }

    public String getFirstParameter() {
        return commandParts[0];
    }

    public boolean isEmpty() {
        return getFirstParameter().length() == 0;
    }

    public boolean startsWith(String identifier) {
        if (hasOnlyOneParameter())
            return false;
        return commandAfterFirstParameter.startsWith(identifier);
    }

    public String afterIdentifier(String identifier) {
        String[] commandParts = commandAfterFirstParameter.split(identifier);
        return commandParts[1];
    }

    public boolean hasOnlyOneParameter() {
        return commandAfterFirstParameter.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}