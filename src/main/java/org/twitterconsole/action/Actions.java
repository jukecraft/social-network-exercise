package org.twitterconsole.action;

import java.util.List;

import org.twitterconsole.io.Command;

public class Actions {
    private List<Action> actions;

    public Actions(List<Action> actions) {
        this.actions = actions;
    }

    public void execute(Command command) {
        actions.stream()
            .forEach(action -> action.execute(command));
    }
}
