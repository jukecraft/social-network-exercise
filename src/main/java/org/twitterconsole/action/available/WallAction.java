package org.twitterconsole.action.available;

import org.twitterconsole.action.output.ActionWithOutput;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class WallAction implements ActionWithOutput {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallAction(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isExecutable(Command command) {
        return command.startsWith(WALL_IDENTIFIER);
    }

    @Override
    public void execute(Command command) {
        executeWithOutput(command);
    }

    @Override
    public WallOutput executeWithOutput(Command commandParameter) {
        return timelineService.collectWall(new User(commandParameter));
    }

}
