package accepting;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

public class SocialTime {
    private LocalDateTime timestamp;

    public SocialTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String printTimestamp(SocialTime timeOfPrinting) {
        Duration timePassed = between(this.timestamp, timeOfPrinting.timestamp);
        return timePassed.toMinutes() + " minutes ago";
    }
}