package command.output;

import io.SocialNetworkingConsole;
import posts.output.Output;
import time.SocialTimeClock;

public class ConsoleCommandObserver implements CommandObserver {

    private SocialNetworkingConsole console;
    private SocialTimeClock clock;

    public ConsoleCommandObserver(SocialNetworkingConsole console, SocialTimeClock clock) {
        this.console = console;
        this.clock = clock;
    }

    @Override
    public void update(Output output) {
        console.print(output.print(clock.getLocalDateTime()));
    }

}
