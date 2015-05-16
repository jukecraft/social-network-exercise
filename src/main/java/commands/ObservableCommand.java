package commands;

import java.util.ArrayList;
import java.util.List;

import timeline.Output;
import application.CommandObserver;

public class ObservableCommand implements Command {
    private Command command;
    private List<CommandObserver> observers = new ArrayList<>();

    public ObservableCommand(Command command) {
        this.command = command;
    }

    public void registerObserver(CommandObserver observer) {
        observers.add(observer);
    }

    @Override
    public Output executeCommand(CommandParameter commandParameter) {
        Output output = command.executeCommand(commandParameter);
        observers.stream().forEach(observer -> observer.update(output));
        return output;
    }

    @Override
    public boolean isApplicable(CommandParameter parameter) {
        return command.isApplicable(parameter);
    }

}