package posts;

import static java.text.MessageFormat.format;

import java.time.Duration;

public class SocialNetworkDuration {
    private static final String TIMESTAMP_PATTERN = "{0} {1} ago";
    private static final String UNIT_ONE_MINUTE = "minute";
    private static final String UNIT_MULTIPLE_MINUTES = "minutes";
    private static final String UNIT_ONE_SECOND = "second";
    private static final String UNIT_MULTIPLE_SECONDS = "seconds";

    private Duration timePassed;

    public SocialNetworkDuration(Duration timePassed) {
        this.timePassed = timePassed;
    }

    @Override
    public String toString() {
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
}
