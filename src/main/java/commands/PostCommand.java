package commands;

import io.CommandParameter;
import time.SocialTimeClock;
import timeline.Message;
import timeline.TimelineService;
import timeline.User;

public class PostCommand implements Command {
    private static final String POSTING_COMMAND = " -> ";

    private TimelineService timelines;
    private SocialTimeClock clock;

    public PostCommand(TimelineService timelines, SocialTimeClock clock) {
        this.timelines = timelines;
        this.clock = clock;
    }

    @Override
    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(POSTING_COMMAND);
    }

    @Override
    public void executeCommand(CommandParameter commandParameter) {
        timelines.post(new User(commandParameter), new Message(commandParameter), clock.getLocalDateTime());
    }
}
