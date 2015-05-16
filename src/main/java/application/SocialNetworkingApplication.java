package application;

import io.Command;
import io.SocialNetworkingConsole;
import action.Actions;

public class SocialNetworkingApplication {

    private SocialNetworkingConsole console;
    private Actions actions;

    public SocialNetworkingApplication(Actions actions, SocialNetworkingConsole console) {
        this.actions = actions;
        this.console = console;
    }

    public void start() {
        console.printPrompt();
        while (true) {
            Command command = console.getNextCommand();
            if (command == Command.NOTHING)
                continue;
            if (command.isEmpty())
                return;
            actions.execute(command);
        }
    }
}
