package action;

import io.Command;

public interface Action {
    boolean isExecutable(Command command);

    void execute(Command command);
}
