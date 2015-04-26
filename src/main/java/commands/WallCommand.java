package commands;

import timeline.TimelineService;
import timeline.User;
import timeline.WallOutput;

public class WallCommand implements Command {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallCommand(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

    public WallOutput executeCommand(CommandParameter wallCommand) {
        return timelineService.collectWall(new User(wallCommand));
    }

}
