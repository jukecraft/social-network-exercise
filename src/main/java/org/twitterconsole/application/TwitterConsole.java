package org.twitterconsole.application;

import java.util.Optional;

import org.twitterconsole.action.Actions;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;

public class TwitterConsole {

    private SocialNetworkingConsole console;
    private Actions actions;

    public TwitterConsole(Actions actions, SocialNetworkingConsole console) {
        this.actions = actions;
        this.console = console;
    }

    public void start() {
        while (true) {
            Optional<Command> optionalCommand = askForUserInput();
            handleUserInput(optionalCommand);
        }
    }

    private Optional<Command> askForUserInput() {
        console.printPrompt();
        return console.getNextCommand();
    }

    private void handleUserInput(Optional<Command> optionalCommand) {
        optionalCommand.ifPresent(command -> {
            if (command.isEmpty())
                System.exit(0);
            actions.execute(command);
        });
    }

}
