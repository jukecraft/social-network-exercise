package org.twitterconsole;

import org.twitterconsole.application.TwitterConsole;
import org.twitterconsole.application.TwitterConsoleFactory;


public class StartTwitterConsole {

    public static void main(String args[]) {
        TwitterConsole application = new TwitterConsoleFactory().create();
        application.start();
    }

}
