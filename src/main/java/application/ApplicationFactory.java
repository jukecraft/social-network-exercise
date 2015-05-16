package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import time.SocialTimeClock;
import timeline.SocialNetwork;
import timeline.TimelineService;
import timeline.Timelines;

import commands.CommandWithOutput;
import commands.Commands;
import commands.FollowCommand;
import commands.ObservableCommand;
import commands.PostCommand;
import commands.TimelineCommand;
import commands.WallCommand;

public class ApplicationFactory {

    private SocialTimeClock clock;
    private TimelineService timelineService;
    private Commands commands;
    private SocialNetworkingConsole console = new SocialNetworkingConsole();

    public static ApplicationFactory standardConfiguration() {
        return new ApplicationFactory().withClock(systemDefaultZone());
    }

    private ApplicationFactory() {
    }

    public ApplicationFactory withClock(Clock clock) {
        this.clock = new SocialTimeClock(clock);
        timelineService = new TimelineService(new Timelines(), new SocialNetwork());
        commands = new Commands(asList( //
            new PostCommand(timelineService, this.clock), //
            createObservableCommand(new TimelineCommand(timelineService)), //
            new FollowCommand(timelineService), //
            createObservableCommand(new WallCommand(timelineService))));
        return this;
    }

    private ObservableCommand createObservableCommand(CommandWithOutput command) {
        ObservableCommand observableCommand = new ObservableCommand(command);
        observableCommand.registerObserver(new ConsoleCommandObserver(console, clock));
        return observableCommand;
    }

    public ApplicationFactory withCommands(Commands commands) {
        this.commands = commands;
        return this;
    }

    public ApplicationFactory withConsole(SocialNetworkingConsole console) {
        this.console = console;
        return this;
    }

    public Commands getCommands() {
        return commands;
    }

    public SocialTimeClock getClock() {
        return clock;
    }

    public SocialNetworkingConsole getConsole() {
        return console;
    }

}
