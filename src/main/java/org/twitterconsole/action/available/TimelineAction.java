package org.twitterconsole.action.available;

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
    public boolean isExecutable(Command command) {
        return command.hasOnlyOneParameter();
    }

    @Override
    public void execute(Command command) {
        executeWithOutput(command);
    }

    @Override
    public Output executeWithOutput(Command command) {
        return timelineService.collectPosts(new User(command));
    }
}
