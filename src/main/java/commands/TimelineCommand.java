package commands;

import time.SocialTime;
import timeline.Output;
import timeline.TimelineService;

public class TimelineCommand implements Command {

    private TimelineService timelines;

    public TimelineCommand(TimelineService timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.isEmpty();
    }

    @Override
    public Output executeCommand(User user, CommandParameter command, SocialTime time) {
        return timelines.printTimeline(user, time);
    }
}
