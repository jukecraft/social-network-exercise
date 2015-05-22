package org.twitterconsole.action.available;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

import org.twitterconsole.action.output.ActionWithOutput;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.Output;

public class TimelineAction implements ActionWithOutput {

    private TimelineService timelineService;

    public TimelineAction(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command))
            executeWithOutput(command);
    }

    @Override
    public Optional<Output> executeWithOutput(Command command) {
        if (isExecutable(command))
            return of(timelineService.collectPosts(new User(command)));
        return empty();
    }

    private boolean isExecutable(Command command) {
        return command.hasOnlyOneParameter();
    }

}
