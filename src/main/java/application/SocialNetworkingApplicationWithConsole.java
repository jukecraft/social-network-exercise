package application;

import commands.CommandParameter;

public class SocialNetworkingApplicationWithConsole {

    private SocialNetworkingConsole console;
    private SocialNetworkingApplication application;

    public SocialNetworkingApplicationWithConsole(ApplicationFactory applicationFactory) {
        console = applicationFactory.getConsole();
        application = applicationFactory.getApplication();
    }

    public void start() {
        console.print("Welcome to my social network application");
        while (true) {
            CommandParameter command = console.getNextCommand();
            if (command == CommandParameter.NOTHING)
                continue;
            if (command.isEmpty())
                return;
            application.accept(command);
        }
    }
}
