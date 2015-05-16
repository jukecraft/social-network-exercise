package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;
import io.ConsoleCommandObserver;
import io.SocialNetworkingConsole;

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
    private SocialNetworkingConsole console = new SocialNetworkingConsole();
    private SocialTimeClock clock = new SocialTimeClock(systemDefaultZone());

    public ApplicationFactory withClock(Clock clock) {
        this.clock = new SocialTimeClock(clock);
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

        return new Commands(asList( //
            new PostCommand(timelineService, this.clock), //
            createObservableCommand(new TimelineCommand(timelineService)), //
            new FollowCommand(timelineService), //
            createObservableCommand(new WallCommand(timelineService))));
    }

    private ObservableCommand createObservableCommand(CommandWithOutput command) {
        ObservableCommand observableCommand = new ObservableCommand(command);
        observableCommand.registerObserver(new ConsoleCommandObserver(console, clock));
        return observableCommand;
    }

}
