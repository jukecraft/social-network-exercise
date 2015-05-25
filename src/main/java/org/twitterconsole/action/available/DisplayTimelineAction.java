package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.UsersPosts;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class DisplayTimelineAction implements Action {

    private UsersPosts usersPosts;
    private ConsoleWithClock consoleWithClock;

    public DisplayTimelineAction(UsersPosts usersPosts, ConsoleWithClock consoleWithClock) {
        this.usersPosts = usersPosts;
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
        PostsOutput output = usersPosts.collectPosts(new User(command));
        consoleWithClock.print(output);
    }

}
