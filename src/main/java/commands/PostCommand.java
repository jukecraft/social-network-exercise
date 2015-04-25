package commands;

import static java.util.Collections.emptyList;

import java.util.List;

import time.SocialTime;
import timeline.Message;
import timeline.Post;
import timeline.Timelines;

public class PostCommand implements Command {

    private static final String POSTING_COMMAND = "->";

    private Timelines timelines;

    public PostCommand(Timelines timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(String command) {
        return command.contains(POSTING_COMMAND);
    }

    @Override
    public List<String> executeCommand(User user, String command, SocialTime time) {
        timelines.post(user, new Post(new Message(command), time));
        return emptyList();
    }
}
