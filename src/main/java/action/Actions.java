package action;

import io.Command;

import java.util.List;

public class Actions {
    private List<Action> actions;

    public Actions(List<Action> actions) {
        this.actions = actions;
    }

    public void execute(Command command) {
        actions.stream() //
            .filter(candidate -> candidate.isExecutable(command)) //
            .findFirst() //
            .ifPresent(action -> action.execute(command));
    }
}
