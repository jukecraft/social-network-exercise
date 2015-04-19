package accepting;

public class MessageParser {

    public Message parse(String string) {
        String[] messageParts = string.split(" -> ");
        if (messageParts.length < 2)
            return new Message("");
        return new Message(messageParts[1]);
    }

}
