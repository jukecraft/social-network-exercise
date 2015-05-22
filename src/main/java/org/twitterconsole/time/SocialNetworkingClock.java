package org.twitterconsole.time;

import static java.time.LocalDateTime.now;

import java.time.Clock;

import org.twitterconsole.posts.SocialTime;

public class SocialNetworkingClock {

    private Clock clock;

    public SocialNetworkingClock(Clock clock) {
        this.clock = clock;
    }

    public SocialTime getLocalDateTime() {
        return new SocialTime(now(clock));
    }

}
