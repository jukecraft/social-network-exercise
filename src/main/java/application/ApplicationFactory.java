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
        return new ApplicationFactory(systemDefaultZone()).withConsole(new SocialNetworkingConsole());
    }

    public ApplicationFactory withClock(SocialTimeClock clock) {
        this.clock = clock;
        timelineService = new TimelineService(new Timelines(), new SocialNetwork(), clock);
        commands = new Commands(asList( //
            new PostCommand(timelineService), //
            new TimelineCommand(timelineService), //
            new FollowCommand(timelineService), //
            new WallCommand(timelineService) //
            ));
        return this;
    }

    public ApplicationFactory(Clock clock) {
        withClock(clock);
    }

    public ApplicationFactory withClock(Clock clock) {
        return withClock(new SocialTimeClock(clock));
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

}
