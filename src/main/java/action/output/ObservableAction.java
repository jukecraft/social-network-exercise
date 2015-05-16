package action.output;

import io.Command;

import java.util.ArrayList;
import java.util.List;

import posts.output.Output;
import action.Action;

public class ObservableAction implements Action {
    private ActionWithOutput action;
    private List<ActionObserver> observers = new ArrayList<>();

    public ObservableAction(ActionWithOutput action) {
        this.action = action;
    }

    public ObservableAction withObserver(ActionObserver observer) {
        observers.add(observer);
        return this;
    }

    @Override
    public void execute(Command command) {
        Output output = action.executeWithOutput(command);
        observers.stream() //
            .forEach(observer -> observer.update(output));
    }

    @Override
    public boolean isExecutable(Command command) {
        return action.isExecutable(command);
    }

}