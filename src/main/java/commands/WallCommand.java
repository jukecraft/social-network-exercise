package commands;

import timeline.Output;
import timeline.TimelineService;

public class WallCommand {

    private static final String WALL_IDENTIFIER = " wall";

    public WallCommand(TimelineService timelineService) {
        // TODO Auto-generated constructor stub
    }

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

    public Output executeCommand(CommandParameter wallCommand) {
        // TODO Auto-generated method stub
        return new Output();
    }

}
