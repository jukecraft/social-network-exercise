package accepting;

import static java.text.MessageFormat.format;
import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

public class SocialTime implements Comparable<SocialTime> {
    private static final String UNIT_ONE_MINUTE = "minute";
    private static final String UNIT_MULTIPLE_MINUTES = "minutes";
    private static final String TIMESTAMP_PATTERN = "{0} {1} ago";

    private LocalDateTime timestamp;

    public SocialTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String printTimestamp(SocialTime timeOfPrinting) {
        Duration timePassed = between(this.timestamp, timeOfPrinting.timestamp);
        return printTimestamp(timePassed.toMinutes());
    }

    private String printTimestamp(long numberOfMinutes) {
        return format(TIMESTAMP_PATTERN, numberOfMinutes, getUnit(numberOfMinutes));
    }

    private String getUnit(long numberOfMinutes) {
        if (numberOfMinutes > 1)
            return UNIT_MULTIPLE_MINUTES;
        return UNIT_ONE_MINUTE;
    }

    @Override
    public int compareTo(SocialTime other) {
        return other.timestamp.compareTo(this.timestamp);
    }
}