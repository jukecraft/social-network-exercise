package application;

import io.Command;
import io.SocialNetworkingConsole;
import action.Actions;

public class Twitter {

    private SocialNetworkingConsole console;
    private Actions actions;

    public Twitter(Actions actions, SocialNetworkingConsole console) {
        this.actions = actions;
        this.console = console;
    }

    public void start() {
        while (true) {
            console.printPrompt();
            Command command = console.getNextCommand();
            if (command == Command.NOTHING)
                continue;
            if (command.isEmpty())
                return;
            actions.execute(command);
        }
    }
}
