package command.output;

import io.SocialNetworkingConsole;
import posts.output.Output;
import time.SocialNetworkingClock;

public class ConsoleCommandObserver implements CommandObserver {

    private SocialNetworkingConsole console;
    private SocialNetworkingClock clock;

    public ConsoleCommandObserver(SocialNetworkingConsole console, SocialNetworkingClock clock) {
        this.console = console;
        this.clock = clock;
    }

    @Override
    public void update(Output output) {
        console.print(output.print(clock.getLocalDateTime()));
    }

}
