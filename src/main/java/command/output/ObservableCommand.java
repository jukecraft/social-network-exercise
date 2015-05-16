package command.output;

import io.CommandParameter;

import java.util.ArrayList;
import java.util.List;

import posts.output.Output;

import command.Command;

public class ObservableCommand implements Command {
    private CommandWithOutput command;
    private List<CommandObserver> observers = new ArrayList<>();

    public ObservableCommand(CommandWithOutput command) {
        this.command = command;
    }

    public void registerObserver(CommandObserver observer) {
        observers.add(observer);
    }

    @Override
    public void executeCommand(CommandParameter commandParameter) {
        Output output = command.executeCommandWithOutput(commandParameter);
        observers.stream().forEach(observer -> observer.update(output));
    }

    @Override
    public boolean isApplicable(CommandParameter parameter) {
        return command.isApplicable(parameter);
    }

}