package application;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplicationWithConsole {

    private SocialNetworkingConsole console;
    private Commands commands;

    public SocialNetworkingApplicationWithConsole(ApplicationFactory applicationFactory) {
        console = applicationFactory.getConsole();
        commands = applicationFactory.getCommands();
    }

    public void start() {
        console.print("Welcome to my social network application");
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
