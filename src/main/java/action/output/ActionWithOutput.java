package action.output;

import io.Command;
import posts.output.Output;
import action.Action;

public interface ActionWithOutput extends Action {

    Output executeWithOutput(Command command);

}
