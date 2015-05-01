package application;

import static application.ApplicationFactory.standardConfiguration;

public class StartApp {

    public static void main(String args[]) {
        SocialNetworkingApplicationWithConsole application = new SocialNetworkingApplicationWithConsole(
            standardConfiguration());
        application.start();
    }

}
