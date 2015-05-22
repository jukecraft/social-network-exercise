package org.twitterconsole.action;

import org.twitterconsole.io.Command;

public interface Action {
    void execute(Command command);
}
