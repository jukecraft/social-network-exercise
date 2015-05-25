package org.twitterconsole.action.available;

import java.util.List;

import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.output.Output;
import org.twitterconsole.time.SocialNetworkingClock;

public class ConsoleWithClock {
    public SocialNetworkingConsole console;
    public SocialNetworkingClock clock;

    public ConsoleWithClock(SocialNetworkingConsole console, SocialNetworkingClock clock) {
        this.console = console;
        this.clock = clock;
    }

    public void print(Output output) {
        SocialTime time = clock.getLocalDateTime();
        List<String> lines = output.print(time);
        console.print(lines);
    }
}