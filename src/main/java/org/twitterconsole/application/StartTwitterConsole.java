package org.twitterconsole.application;


public class StartTwitterConsole {

    public static void main(String args[]) {
        Twitter application = new TwitterFactory().create();
        application.start();
    }

}
