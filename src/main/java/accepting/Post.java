package accepting;

import java.time.LocalDateTime;

public class Post {

    private Message message;

    public Post(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message.toString();
    }

    public String printTimestamp(LocalDateTime plusMinutes) {
        return " (5 minutes ago)";
    }

}
