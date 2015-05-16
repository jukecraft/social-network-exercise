package application;

import static application.ApplicationFactory.standardConfiguration;

public class StartApp {

    public static void main(String args[]) {
        SocialNetworkingApplication application = standardConfiguration().create();
        application.start();
    }

}
