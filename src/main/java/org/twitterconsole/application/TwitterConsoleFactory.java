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

public class TwitterConsoleFactory {
    private SocialNetworkingConsole console = new SocialNetworkingConsole();
    private SocialNetworkingClock clock = new SocialNetworkingClock(systemDefaultZone());

    public TwitterConsoleFactory withClock(Clock clock) {
        this.clock = new SocialNetworkingClock(clock);
        return this;
    }

    public TwitterConsoleFactory withConsole(SocialNetworkingConsole console) {
        this.console = console;
        return this;
    }

    public TwitterConsole create() {
        return new TwitterConsole(getActions(), console);
    }

    public Actions getActions() {
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
