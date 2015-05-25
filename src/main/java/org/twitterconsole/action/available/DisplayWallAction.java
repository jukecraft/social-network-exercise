package org.twitterconsole.action.available;

import java.util.List;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;
import org.twitterconsole.time.SocialNetworkingClock;

public class DisplayWallAction implements Action {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;
    private SocialNetworkingConsole console;
    private SocialNetworkingClock clock;

    public DisplayWallAction(TimelineService timelineService, SocialNetworkingConsole console,
        SocialNetworkingClock clock) {
        this.timelineService = timelineService;
        this.console = console;
        this.clock = clock;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command)) {
            printWall(command);
        }
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(WALL_IDENTIFIER);
    }

    private void printWall(Command command) {
        WallOutput wallOutput = timelineService.collectWall(new User(command));
        List<String> output = wallOutput.print(clock.getLocalDateTime());
        console.print(output);
    }

}
