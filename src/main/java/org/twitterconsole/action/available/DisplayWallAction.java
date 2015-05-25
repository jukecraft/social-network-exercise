package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class DisplayWallAction implements Action {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;
    private ConsoleWithClock consoleWithClock;

    public DisplayWallAction(TimelineService timelineService, ConsoleWithClock consoleWithClock) {
        this.timelineService = timelineService;
        this.consoleWithClock = consoleWithClock;
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
        consoleWithClock.print(wallOutput);
    }

}
