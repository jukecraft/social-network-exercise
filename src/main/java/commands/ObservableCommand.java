package commands;

import timeline.Output;

public class ObservableCommand {
    private Command command;

    public ObservableCommand(Command command) {
        this.command = command;

    }

    public Output executeCommand(CommandParameter commandParameter) {
        return command.executeCommand(commandParameter);
    }

}