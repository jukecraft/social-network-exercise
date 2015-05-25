package org.twitterconsole.action.available;

import java.util.List;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;
import org.twitterconsole.time.SocialNetworkingClock;

public class DisplayTimelineAction implements Action {

    private TimelineService timelineService;
    private SocialNetworkingConsole console;
    private SocialNetworkingClock clock;

    public DisplayTimelineAction(TimelineService timelineService, SocialNetworkingClock clock,
        SocialNetworkingConsole console) {
        this.timelineService = timelineService;
        this.console = console;
        this.clock = clock;
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
        PostsOutput postsOutput = timelineService.collectPosts(new User(command));
        List<String> output = postsOutput.print(clock.getLocalDateTime());
        console.print(output);
    }

}
