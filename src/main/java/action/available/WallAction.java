package action.available;

import io.Command;
import network.TimelineService;
import posts.User;
import posts.output.WallOutput;
import actipon.output.ActionWithOutput;

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
