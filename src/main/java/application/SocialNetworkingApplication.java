package application;

import java.util.ArrayList;
import java.util.List;

import time.SocialTimeClock;

import commands.Commands;

public class SocialNetworkingApplication {

    private List<String> output = new ArrayList<>();
    private SocialTimeClock clock;
    private Commands commands;

    public SocialNetworkingApplication(ApplicationFactory factory) {
        this.clock = factory.getClock();
        commands = factory.getCommands();
    }

    public void accept(String command) {
        output = commands.execute(command, clock.getLocalDateTime());
    }

    public List<String> getOutput() {
        return output;
    }

}
