package accepting.builder;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

import accepting.SocialTime;

public class SocialTimeBuilder {

    private LocalDateTime timestamp = now();

    public static SocialTimeBuilder aTime() {
        return new SocialTimeBuilder();
    }

    public SocialTimeBuilder withTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public SocialTimeBuilder plusMinutes(int numberOfMinutes) {
        timestamp = timestamp.plusMinutes(numberOfMinutes);
        return this;
    }

    public SocialTime create() {
        return new SocialTime(timestamp);
    }

}
