package commands;

import timeline.Output;
import timeline.TimelineService;
import timeline.User;

public class TimelineCommand implements CommandWithOutput {

    private TimelineService timelines;

    public TimelineCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.isEmptyWithoutUser();
    }

    @Override
    public void executeCommand(CommandParameter commandParameter) {
        executeCommandWithOutput(commandParameter);
    }

    @Override
    public Output executeCommandWithOutput(CommandParameter commandParameter) {
        return timelines.collectPosts(new User(commandParameter));
    }
}
