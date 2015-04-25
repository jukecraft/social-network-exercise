package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import time.SocialTimeClock;
import timeline.Timelines;

import commands.Commands;
import commands.PostCommand;
import commands.TimelineCommand;

public class ApplicationFactory {

    private SocialTimeClock clock = new SocialTimeClock(systemDefaultZone());
    private Timelines timelines = new Timelines();
    private Commands commands = new Commands(asList(new PostCommand(timelines), new TimelineCommand(timelines)));

    public static ApplicationFactory standardConfiguration() {
        return new ApplicationFactory();
    }

    public ApplicationFactory withClock(Clock clock) {
        this.clock = new SocialTimeClock(clock);
        return this;
    }

    public ApplicationFactory withClock(SocialTimeClock clock) {
        this.clock = clock;
        return this;
    }

    public ApplicationFactory withCommands(Commands commands) {
        this.commands = commands;
        return this;
    }

    public Commands getCommands() {
        return commands;
    }

    public SocialTimeClock getClock() {
        return clock;
    }

}
