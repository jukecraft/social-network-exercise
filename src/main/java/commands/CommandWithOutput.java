package commands;

import timeline.Output;

public interface CommandWithOutput extends Command {

    Output executeCommandWithOutput(CommandParameter commandParameter);

}
