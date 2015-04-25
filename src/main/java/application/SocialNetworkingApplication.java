package application;

import static java.time.LocalDateTime.now;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import accepting.Message;
import accepting.Post;
import accepting.SocialTime;
import accepting.Timeline;

public class SocialNetworkingApplication {
    private static final String POSTING_COMMAND = "->";

    private Timeline posts = new Timeline();
    private List<String> output = new ArrayList<>();
    private Clock clock;

    public SocialNetworkingApplication(Clock clock) {
        this.clock = clock;
    }

    public void accept(String message) {
        if (message.contains(POSTING_COMMAND))
            addNewPost(message);
        else
            addTimelineToOutput();
    }

    private void addTimelineToOutput() {
        output.addAll(posts.printTimeline(getCurrentTime()));
    }

    private void addNewPost(String message) {
        posts.addPost(createNewPost(message));
    }

    private Post createNewPost(String message) {
        return new Post(new Message(message), getCurrentTime());
    }

    private SocialTime getCurrentTime() {
        return new SocialTime(now(clock));
    }

    public List<String> getOutput() {
        return output;
    }

}
