package commands;

import time.SocialTime;
import timeline.Output;
import timeline.Timelines;

public class TimelineCommand implements Command {

    private Timelines timelines;

    public TimelineCommand(Timelines timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(String command) {
        return command.length() == 0;
    }

    @Override
    public Output executeCommand(User user, String command, SocialTime time) {
        return timelines.printTimeline(user, time);
    }
}
