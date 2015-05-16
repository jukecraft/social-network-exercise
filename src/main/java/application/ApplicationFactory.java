package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;
import io.SocialNetworkingConsole;

import java.time.Clock;

import network.SocialNetwork;
import network.TimelineService;
import network.Timelines;
import time.SocialNetworkingClock;
import action.Actions;
import action.available.FollowAction;
import action.available.PostAction;
import action.available.TimelineAction;
import action.available.WallAction;
import action.output.ConsoleObserver;
import action.output.ObservableAction;

public class ApplicationFactory {
    private SocialNetworkingConsole console = new SocialNetworkingConsole();
    private SocialNetworkingClock clock = new SocialNetworkingClock(systemDefaultZone());

    public ApplicationFactory withClock(Clock clock) {
        this.clock = new SocialNetworkingClock(clock);
        return this;
    }

    public ApplicationFactory withConsole(SocialNetworkingConsole console) {
        this.console = console;
        return this;
    }

    public SocialNetworkingApplication create() {
        return new SocialNetworkingApplication(getActions(), console);
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
