package org.twitterconsole.action.output;

import java.util.Optional;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.posts.output.Output;

public interface ActionWithOutput extends Action {

    Optional<Output> executeWithOutput(Command command);

}
