package application;

import static java.time.LocalDateTime.now;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import accepting.Command;
import accepting.SocialTime;
import accepting.Timelines;

public class SocialNetworkingApplication {
    private static final String POSTING_COMMAND = "->";
    private Timelines timelines = new Timelines();
    private List<String> output = new ArrayList<>();
    private Clock clock;

    public SocialNetworkingApplication(Clock clock) {
        this.clock = clock;
    }

    public void accept(String message) {
        if (message.contains(POSTING_COMMAND))
            addNewPost(message);
        else
            addTimelineToOutput(message);
    }

    private void addTimelineToOutput(String message) {
        Command command = new Command(message + " -> qre");
        output.addAll(timelines.getPostsFor(command.getUser()).printTimeline(getCurrentTime()));
    }

    private void addNewPost(String message) {
        Command command = new Command(message);
        timelines.post(command.getUser(), command.createPost(getCurrentTime()));
    }

    private SocialTime getCurrentTime() {
        return new SocialTime(now(clock));
    }

    public List<String> getOutput() {
        return output;
    }

}
