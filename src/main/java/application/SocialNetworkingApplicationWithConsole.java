package application;

import java.util.Scanner;

public class SocialNetworkingApplicationWithConsole {

    private SocialNetworkingConsole console;
    private SocialNetworkingApplication application;

    public SocialNetworkingApplicationWithConsole(ApplicationFactory applicationFactory) {
        console = applicationFactory.getConsole();
        application = applicationFactory.getApplication();
    }

    public void start() {
        console.print("Welcome to my social network application");
        Scanner scanner = new Scanner(System.in);
        try {
            if (scanner.hasNext()) {
                application.accept(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
    }

}
