package application;

import java.util.Optional;

public class SocialNetworkingApplicationWithConsole {

    private SocialNetworkingConsole console;
    private SocialNetworkingApplication application;

    public SocialNetworkingApplicationWithConsole(ApplicationFactory applicationFactory) {
        console = applicationFactory.getConsole();
        application = applicationFactory.getApplication();
    }

    public void start() {
        console.print("Welcome to my social network application");
        Optional<String> command = console.getNextCommand();
        if (command.isPresent()) {
            application.accept(command.get());
        }
    }
}
