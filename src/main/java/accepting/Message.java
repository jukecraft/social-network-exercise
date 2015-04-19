package accepting;


public class Message {
    private String message;

    public static Message emptyMessage() {
        return new Message("");
    }

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
