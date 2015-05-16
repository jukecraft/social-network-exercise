package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;
import io.SocialNetworkingConsole;

import java.time.Clock;

import network.SocialNetwork;
import network.TimelineService;
import network.Timelines;
import time.SocialNetworkingClock;

import command.Commands;
import command.available.FollowCommand;
import command.available.PostCommand;
import command.available.TimelineCommand;
import command.available.WallCommand;
import command.output.ConsoleCommandObserver;
import command.output.ObservableCommand;

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
        return new SocialNetworkingApplication(getCommands(), console);
    }

    public Commands getCommands() {
        TimelineService timelineService = new TimelineService(new Timelines(), new SocialNetwork());
        ConsoleCommandObserver observer = new ConsoleCommandObserver(console, clock);

        return new Commands(asList( //
            new PostCommand(timelineService, clock), //
            new ObservableCommand(new TimelineCommand(timelineService)) //
                .withObserver(observer), //
            new FollowCommand(timelineService), //
            new ObservableCommand(new WallCommand(timelineService)) //
                .withObserver(observer)));
    }

}
