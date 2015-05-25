package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class DisplayTimelineAction implements Action {

    private PostRepository userRepository;
    private ConsoleWithClock consoleWithClock;

    public DisplayTimelineAction(PostRepository userRepository, ConsoleWithClock consoleWithClock) {
        this.userRepository = userRepository;
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
        PostsOutput output = userRepository.collectPosts(new User(command));
        consoleWithClock.print(output);
    }

}
