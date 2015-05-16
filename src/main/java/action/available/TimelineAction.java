package action.available;

import io.Command;
import network.TimelineService;
import posts.User;
import posts.output.Output;
import actipon.output.ActionWithOutput;

public class TimelineAction implements ActionWithOutput {

    private TimelineService timelineService;

    public TimelineAction(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isExecutable(Command command) {
        return command.isEmptyWithoutUser();
    }

    @Override
    public void execute(Command command) {
        executeWithOutput(command);
    }

    @Override
    public Output executeWithOutput(Command command) {
        return timelineService.collectPosts(new User(command));
    }
}
