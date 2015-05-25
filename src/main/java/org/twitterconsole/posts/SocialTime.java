package org.twitterconsole.posts;

import static java.time.Duration.between;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.time.Duration;
import java.time.LocalDateTime;

import org.twitterconsole.io.SocialNetworkDuration;

public class SocialTime implements Comparable<SocialTime> {

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

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

}