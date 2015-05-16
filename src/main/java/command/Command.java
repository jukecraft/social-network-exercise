package command;

import io.CommandParameter;


public interface Command {
    boolean isApplicable(CommandParameter commandParameter);

    void executeCommand(CommandParameter commandParameter);
}
