package accepting;

import java.time.LocalDateTime;

public class Post {

    private Message message;

    public Post(Message message) {
        this.message = message;
    }

    public Post(Message message, LocalDateTime postingTime) {
    }

    @Override
    public String toString() {
        return message.toString();
    }

    public String printTimestamp(LocalDateTime currentTime) {
        return " (5 minutes ago)";
    }

}
