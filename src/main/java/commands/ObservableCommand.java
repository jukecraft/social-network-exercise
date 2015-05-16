package commands;

import timeline.Output;
import application.CommandObserver;

public class ObservableCommand {
    private Command command;

    public ObservableCommand(Command command) {
        this.command = command;

    }

    public Output executeCommand(CommandParameter commandParameter) {
        return command.executeCommand(commandParameter);
    }

    public void registerObserver(CommandObserver observer) {
        // TODO Auto-generated method stub

    }

}