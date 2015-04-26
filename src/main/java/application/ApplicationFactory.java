package application;

import static java.time.Clock.systemDefaultZone;
import static java.util.Arrays.asList;

import java.time.Clock;

import time.SocialTimeClock;
import timeline.TimelineService;
import timeline.Timelines;

import commands.Commands;
import commands.PostCommand;
import commands.TimelineCommand;

public class ApplicationFactory {

    private SocialTimeClock clock;
    private TimelineService timelineService;
    private Commands commands;

    public static ApplicationFactory standardConfiguration() {
        return new ApplicationFactory(systemDefaultZone());
    }

    public ApplicationFactory(Clock clock) {
        withClock(clock);
    }

    public ApplicationFactory withClock(Clock clock) {
        return withClock(new SocialTimeClock(clock));
    }

    public ApplicationFactory withClock(SocialTimeClock clock) {
        this.clock = clock;
        timelineService = new TimelineService(new Timelines(), clock);
        commands = new Commands(asList(new PostCommand(timelineService), new TimelineCommand(timelineService)));
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
