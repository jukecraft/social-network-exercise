package commands;

import timeline.PostsOutput;

public interface Command {
    boolean isApplicable(CommandParameter commandParameter);

    PostsOutput executeCommand(CommandParameter commandParameter);
}
