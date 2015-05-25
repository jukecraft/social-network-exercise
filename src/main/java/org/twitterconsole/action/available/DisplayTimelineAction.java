package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class DisplayTimelineAction implements Action {

    private TimelineService timelineService;
    private ConsoleWithClock consoleWithClock;

    public DisplayTimelineAction(TimelineService timelineService, ConsoleWithClock consoleWithClock) {
        this.timelineService = timelineService;
        this.consoleWithClock = consoleWithClock;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command))
            printTimeline(command);
    }

    private boolean isExecutable(Command command) {
        return command.hasOnlyOneParameter();
    }

    private void printTimeline(Command command) {
        PostsOutput output = timelineService.collectPosts(new User(command));
        consoleWithClock.print(output);
    }

}
