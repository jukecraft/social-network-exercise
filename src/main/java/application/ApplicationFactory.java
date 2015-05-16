package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import time.SocialTimeClock;
import timeline.SocialNetwork;
import timeline.TimelineService;
import timeline.Timelines;

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
    private SocialNetworkingConsole console;
    private SocialNetworkingApplication application;

    public static ApplicationFactory standardConfiguration() {
        return new ApplicationFactory().withClock(systemDefaultZone()).withConsole(new SocialNetworkingConsole());
    }

    private ApplicationFactory() {
    }

    public ApplicationFactory withClock(Clock clock) {
        this.clock = new SocialTimeClock(clock);
        timelineService = new TimelineService(new Timelines(), new SocialNetwork(), this.clock);
        commands = new Commands(asList( //
            new PostCommand(timelineService), //
            new ObservableCommand(new TimelineCommand(timelineService)), //
            new FollowCommand(timelineService), //
            new ObservableCommand(new WallCommand(timelineService)) //
            ));
        application = new SocialNetworkingApplication(this.clock, commands);
        return this;
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

    public ApplicationFactory withApplication(SocialNetworkingApplication application) {
        this.application = application;
        return this;
    }

    public SocialNetworkingApplication getApplication() {
        return application;
    }

}
