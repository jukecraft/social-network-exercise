package commands;

import timeline.Output;
import timeline.TimelineService;
import timeline.User;

public class WallCommand {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallCommand(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

    public Output executeCommand(CommandParameter wallCommand) {
        return timelineService.collectWall(new User(wallCommand));
    }

}
