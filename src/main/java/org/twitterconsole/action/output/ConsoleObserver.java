package org.twitterconsole.action.output;

import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.posts.output.Output;
import org.twitterconsole.time.SocialNetworkingClock;

public class ConsoleObserver implements ActionObserver {

    private SocialNetworkingConsole console;
    private SocialNetworkingClock clock;

    public ConsoleObserver(SocialNetworkingConsole console, SocialNetworkingClock clock) {
        this.console = console;
        this.clock = clock;
    }

    @Override
    public void update(Output output) {
        console.print(output.print(clock.getLocalDateTime()));
    }

}
