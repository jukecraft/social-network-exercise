package commands;

import java.util.List;

import accepting.SocialTime;
import accepting.Timelines;
import accepting.User;

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
    public List<String> executeCommand(User user, String command, SocialTime time) {
        return timelines.printTimeline(user, time);
    }
}
