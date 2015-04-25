package accepting;

public class MessageParser {
    private static final String MESSAGE_SEPARATOR = " -> ";

    public Message parse(String message) {
        String[] messageParts = message.split(MESSAGE_SEPARATOR);
        return new Message(messageParts[1]);
    }

}
