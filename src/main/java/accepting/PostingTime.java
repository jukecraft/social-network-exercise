package accepting;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

public class PostingTime {
    private LocalDateTime timestamp;

    public PostingTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String printTimestamp(PostingTime timeOfPrinting) {
        Duration timePassed = between(this.timestamp, timeOfPrinting.timestamp);
        return timePassed.toMinutes() + " minutes ago";
    }
}