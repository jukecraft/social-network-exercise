package command.available;

import io.CommandParameter;
import network.TimelineService;
import posts.Message;
import posts.User;
import time.SocialNetworkingClock;

import command.Command;

public class PostCommand implements Command {
    private static final String POSTING_COMMAND = " -> ";

    private TimelineService timelines;
    private SocialNetworkingClock clock;

    public PostCommand(TimelineService timelines, SocialNetworkingClock clock) {
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
