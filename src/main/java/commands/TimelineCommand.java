package commands;

import timeline.PostsOutput;
import timeline.TimelineService;
import timeline.User;

public class TimelineCommand implements CommandWithOutput {

    private TimelineService timelines;

    public TimelineCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.isEmpty();
    }

    @Override
    public PostsOutput executeCommand(CommandParameter command) {
        return timelines.collectPosts(new User(command));
    }
}
