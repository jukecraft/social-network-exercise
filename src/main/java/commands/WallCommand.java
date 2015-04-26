package commands;

import timeline.PostsOutput;
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

    public PostsOutput executeCommand(CommandParameter wallCommand) {
        return timelineService.collectWall(new User(wallCommand));
    }

}
