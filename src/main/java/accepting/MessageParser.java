package accepting;

import static accepting.Message.emptyMessage;

public class MessageParser {

    private static final String MESSAGE_SEPARATOR = " -> ";
    private static final int MINIMUM_MESSAGE_PARTS = 2;

    public Message parse(String message) {
        String[] messageParts = message.split(MESSAGE_SEPARATOR);
        if (messageParts.length < MINIMUM_MESSAGE_PARTS)
            return emptyMessage();
        return new Message(messageParts[1]);
    }

}
