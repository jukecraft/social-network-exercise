package application;

import java.util.List;

import time.SocialTimeClock;
import timeline.Output;
import timeline.PostsOutput;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplication {

    private Output output = new PostsOutput();
    private SocialTimeClock clock;
    private Commands commands;

    public SocialNetworkingApplication(SocialTimeClock clock, Commands commands) {
        this.clock = clock;
        this.commands = commands;
    }

    public void accept(String command) {
        output = commands.execute(new CommandParameter(command));
    }

    public List<String> getOutput() {
        return output.print(clock.getLocalDateTime());
    }

}
