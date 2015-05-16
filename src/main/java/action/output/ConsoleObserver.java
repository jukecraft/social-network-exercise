package action.output;

import io.SocialNetworkingConsole;
import posts.output.Output;
import time.SocialNetworkingClock;

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
