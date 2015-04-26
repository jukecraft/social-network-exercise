package commands;

import time.SocialTime;
import timeline.Message;
import timeline.Output;
import timeline.Post;
import timeline.Timelines;

public class PostCommand implements Command {

    private static final String POSTING_COMMAND = " -> ";

    private Timelines timelines;

    public PostCommand(Timelines timelines) {
        this.timelines = timelines;
    }

    @Override
    public boolean isApplicable(CommandParameter command) {
        return command.startsWith(POSTING_COMMAND);
    }

    @Override
    public Output executeCommand(User user, CommandParameter command, SocialTime time) {
        timelines.post(user, new Post(new Message(command), time));
        return new Output();
    }
}
