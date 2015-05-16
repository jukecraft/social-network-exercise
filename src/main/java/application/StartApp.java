package application;


public class StartApp {

    public static void main(String args[]) {
        SocialNetworkingApplication application = new ApplicationFactory().create();
        application.start();
    }

}
