package time;

import static java.text.MessageFormat.format;
import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

import commons.SocialNetworkingValueObject;

public class SocialTime extends SocialNetworkingValueObject implements Comparable<SocialTime> {
    private static final String TIMESTAMP_PATTERN = "{0} {1} ago";
    private static final String UNIT_ONE_MINUTE = "minute";
    private static final String UNIT_MULTIPLE_MINUTES = "minutes";
    private static final String UNIT_ONE_SECOND = "second";
    private static final String UNIT_MULTIPLE_SECONDS = "seconds";

    private final LocalDateTime timestamp;

    public SocialTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String printTimestamp(SocialTime timeOfPrinting) {
        Duration timePassed = between(this.timestamp, timeOfPrinting.timestamp);
        if (timePassed.getSeconds() >= 60)
            return printTimestamp(timePassed.toMinutes());
        return printTimestampFromSeconds(timePassed.getSeconds());
    }

    private String printTimestampFromSeconds(long numberOfSeconds) {
        return format(TIMESTAMP_PATTERN, numberOfSeconds, getSecondsUnit(numberOfSeconds));
    }

    private Object getSecondsUnit(long numberOfSeconds) {
        if (numberOfSeconds > 1)
            return UNIT_MULTIPLE_SECONDS;
        return UNIT_ONE_SECOND;
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