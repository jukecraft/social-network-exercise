package application;

import java.util.List;

import time.SocialTimeClock;
import timeline.Output;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplication {

    private Output output = new Output();
    private SocialTimeClock clock;
    private Commands commands;

    public SocialNetworkingApplication(ApplicationFactory factory) {
        this.clock = factory.getClock();
        commands = factory.getCommands();
    }

    public void accept(String command) {
        output = commands.execute(new CommandParameter(command), clock.getLocalDateTime());
    }

    public List<String> getOutput() {
        return output.getOutput(clock.getLocalDateTime());
    }

}
