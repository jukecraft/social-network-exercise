package org.twitterconsole.application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import org.twitterconsole.action.Actions;
import org.twitterconsole.action.available.FollowAction;
import org.twitterconsole.action.available.PostAction;
import org.twitterconsole.action.available.TimelineAction;
import org.twitterconsole.action.available.WallAction;
import org.twitterconsole.action.output.ConsoleObserver;
import org.twitterconsole.action.output.ObservableAction;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.network.Timelines;
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
        TimelineService timelineService = new TimelineService(new Timelines(), new SocialNetwork());
        ConsoleObserver observer = new ConsoleObserver(console, clock);

        return new Actions(asList( //
            new PostAction(timelineService, clock), //
            new ObservableAction(new TimelineAction(timelineService)) //
                .withObserver(observer), //
            new FollowAction(timelineService), //
            new ObservableAction(new WallAction(timelineService)) //
                .withObserver(observer)));
    }
}
