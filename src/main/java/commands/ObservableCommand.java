package commands;

import timeline.Output;
import application.CommandObserver;

public class ObservableCommand {
    private Command command;
    private CommandObserver observer;

    public ObservableCommand(Command command) {
        this.command = command;
    }

    public Output executeCommand(CommandParameter commandParameter) {
        Output output = command.executeCommand(commandParameter);
        observer.update(output);
        return output;
    }

    public void registerObserver(CommandObserver observer) {
        this.observer = observer;
    }

}