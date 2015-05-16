package commands;

import timeline.Message;
import timeline.TimelineService;
import timeline.User;

public class PostCommand implements Command {
    private static final String POSTING_COMMAND = " -> ";

    private TimelineService timelines;

    public PostCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(POSTING_COMMAND);
    }

    @Override
    public void executeCommand(CommandParameter commandParameter) {
        timelines.post(new User(commandParameter), new Message(commandParameter));
    }
}
