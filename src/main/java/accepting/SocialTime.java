package accepting;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

public class SocialTime implements Comparable<SocialTime> {
    private LocalDateTime timestamp;

    public SocialTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String printTimestamp(SocialTime timeOfPrinting) {
        Duration timePassed = between(this.timestamp, timeOfPrinting.timestamp);
        long numberOfMinutes = timePassed.toMinutes();
        return numberOfMinutes + " " + getTimestring(numberOfMinutes) + " ago";
    }

    private String getTimestring(long numberOfMinutes) {
        if (numberOfMinutes > 1)
            return "minutes";
        return "minute";
    }

    @Override
    public int compareTo(SocialTime other) {
        return other.timestamp.compareTo(this.timestamp);
    }
}