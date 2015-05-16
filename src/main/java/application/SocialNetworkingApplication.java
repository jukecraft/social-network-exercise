package application;

import io.CommandParameter;
import io.SocialNetworkingConsole;

import command.Commands;

public class SocialNetworkingApplication {

    private SocialNetworkingConsole console;
    private Commands commands;

    public SocialNetworkingApplication(Commands commands, SocialNetworkingConsole console) {
        this.commands = commands;
        this.console = console;
    }

    public void start() {
        while (true) {
            CommandParameter command = console.getNextCommand();
            if (command == CommandParameter.NOTHING)
                continue;
            if (command.isEmpty())
                return;
            commands.execute(command);
        }
    }
}
