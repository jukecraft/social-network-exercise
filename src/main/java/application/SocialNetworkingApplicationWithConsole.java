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
        while (true) {
            Optional<String> command = console.getNextCommand();
            if (!command.isPresent())
                continue;
            if (command.get().length() == 0)
                return;
            application.accept(command.get());
        }
    }
}
