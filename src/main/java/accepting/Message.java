package accepting;

import java.time.LocalDateTime;

public class Message {
    private String message;

    public static Message emptyMessage() {
        return new Message("");
    }

    public Message(String message) {
        this.message = message;
    }

    public Message(String string, LocalDateTime postingTime) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return message;
    }

}
