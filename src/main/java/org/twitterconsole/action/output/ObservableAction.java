package org.twitterconsole.action.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.posts.output.Output;

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
        Optional<Output> optionalOutput = action.executeWithOutput(command);
        optionalOutput.ifPresent(output ->
            observers.stream()
                .forEach(observer -> observer.update(output)));
    }

}