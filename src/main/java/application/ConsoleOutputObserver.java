package application;

import time.SocialTimeClock;
import timeline.Output;

public class ConsoleOutputObserver implements CommandObserver {

    private SocialNetworkingConsole console;
    private SocialTimeClock clock;

    public ConsoleOutputObserver(SocialNetworkingConsole console, SocialTimeClock clock) {
        this.console = console;
        this.clock = clock;
    }

    @Override
    public void update(Output output) {
        console.print(output.print(clock.getLocalDateTime()));
    }

}
