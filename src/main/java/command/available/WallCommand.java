package command.available;

import io.CommandParameter;
import network.TimelineService;
import posts.User;
import posts.output.WallOutput;

import command.output.CommandWithOutput;

public class WallCommand implements CommandWithOutput {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallCommand(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

    @Override
    public void executeCommand(CommandParameter commandParameter) {
        executeCommandWithOutput(commandParameter);
    }

    @Override
    public WallOutput executeCommandWithOutput(CommandParameter commandParameter) {
        return timelineService.collectWall(new User(commandParameter));
    }

}
