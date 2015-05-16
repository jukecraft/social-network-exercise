package command.output;

import io.CommandParameter;
import posts.output.Output;

import command.Command;

public interface CommandWithOutput extends Command {

    Output executeCommandWithOutput(CommandParameter commandParameter);

}
