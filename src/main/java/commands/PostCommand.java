package commands;

import timeline.Message;
import timeline.PostsOutput;
import timeline.TimelineService;
import timeline.User;

public class PostCommand implements Command {
    private static final String POSTING_COMMAND = " -> ";

    private TimelineService timelines;

    public PostCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.startsWith(POSTING_COMMAND);
    }

    @Override
    public PostsOutput executeCommand(CommandParameter command) {
        timelines.post(new User(command), new Message(command));
        return new PostsOutput();
    }
}
