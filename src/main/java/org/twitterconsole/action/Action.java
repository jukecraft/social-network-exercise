package org.twitterconsole.action;

import org.twitterconsole.io.Command;

public interface Action {
    boolean isExecutable(Command command);

    void execute(Command command);
}
