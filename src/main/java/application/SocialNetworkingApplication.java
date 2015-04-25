package application;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import accepting.SocialTime;
import accepting.Timelines;

import commands.Commands;
import commands.PostCommand;
import commands.TimelineCommand;

public class SocialNetworkingApplication {

    private List<String> output = new ArrayList<>();
    private Clock clock;
    private Commands commands;

    public SocialNetworkingApplication(Clock clock) {
        this.clock = clock;
        Timelines timelines = new Timelines();
        commands = new Commands(asList(new PostCommand(timelines), new TimelineCommand(timelines)));
    }

    public void accept(String command) {
        output = commands.execute(command, getCurrentTime());
    }

    private SocialTime getCurrentTime() {
        return new SocialTime(now(clock));
    }

    public List<String> getOutput() {
        return output;
    }

}
