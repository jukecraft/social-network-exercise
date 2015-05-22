package org.twitterconsole.action.output;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.posts.output.Output;

public interface ActionWithOutput extends Action {

    Output executeWithOutput(Command command);

}
