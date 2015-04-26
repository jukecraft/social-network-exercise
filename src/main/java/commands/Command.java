package commands;

import timeline.Output;

public interface Command {
    boolean isApplicable(CommandParameter commandParameter);

    Output executeCommand(User user, CommandParameter commandParameter);
}
