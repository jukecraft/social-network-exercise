package application;

public class SocialNetworkingApplicationWithConsole {

    private SocialNetworkingConsole console;

    public SocialNetworkingApplicationWithConsole(ApplicationFactory applicationFactory) {
        console = applicationFactory.getConsole();
    }

    public void start() {
        console.print("Welcome to my social network application");
    }

}
