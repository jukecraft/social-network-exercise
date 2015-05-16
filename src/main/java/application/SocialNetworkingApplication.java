package application;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplication {

    private Commands commands;

    public SocialNetworkingApplication(Commands commands) {
        this.commands = commands;
    }

    public void accept(CommandParameter command) {
        commands.execute(command);
    }

}
