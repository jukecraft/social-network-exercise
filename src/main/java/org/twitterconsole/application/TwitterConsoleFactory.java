package org.twitterconsole.application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import org.twitterconsole.action.Actions;
import org.twitterconsole.action.available.ConsoleWithClock;
import org.twitterconsole.action.available.DisplayTimelineAction;
import org.twitterconsole.action.available.DisplayWallAction;
import org.twitterconsole.action.available.FollowAction;
import org.twitterconsole.action.available.PostAction;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.UserRepository;
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.time.SocialNetworkingClock;

public final class TwitterConsoleFactory {
    private TwitterConsoleFactory() {
    }

    public static TwitterConsole createTwitterConsole() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();
        Actions actions = createStandardActions(systemDefaultZone(), console);
        return new TwitterConsole(actions, console);
    }

    public static Actions createStandardActions(Clock clock, SocialNetworkingConsole console) {
        return getActions(new SocialNetworkingClock(clock), console);
    }

    private static Actions getActions(SocialNetworkingClock clock, SocialNetworkingConsole console) {
        PostRepository postRepository = new PostRepository();
        UserRepository userRepository = new UserRepository();
        ConsoleWithClock consoleWithClock = new ConsoleWithClock(console, clock);

        return new Actions(asList(
            new PostAction(postRepository, clock),
            new DisplayTimelineAction(postRepository, consoleWithClock),
            new FollowAction(userRepository),
            new DisplayWallAction(postRepository, userRepository, consoleWithClock)));
    }
}
