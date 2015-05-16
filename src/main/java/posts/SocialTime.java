package posts;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

import commons.SocialNetworkingValueObject;

public class SocialTime extends SocialNetworkingValueObject implements Comparable<SocialTime> {

    private final LocalDateTime timestamp;

    public SocialTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(SocialTime other) {
        return other.timestamp.compareTo(this.timestamp);
    }

    public String printTimestamp(SocialTime timeOfPrinting) {
        Duration timePassed = between(timestamp, timeOfPrinting.timestamp);
        return new SocialNetworkDuration(timePassed).toString();
    }

}