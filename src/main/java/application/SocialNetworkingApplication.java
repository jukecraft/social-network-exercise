package application;

import static java.time.LocalDateTime.now;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import accepting.MessageParser;
import accepting.Post;
import accepting.Posts;
import accepting.SocialTime;

public class SocialNetworkingApplication {
    private static final String POSTING_COMMAND = "->";

    private Posts posts = new Posts();
    private List<String> output = new ArrayList<>();
    private Clock clock;
    private MessageParser parser = new MessageParser();

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
        output.addAll(posts.printPosts(getCurrentTime()));
    }

    private void addNewPost(String message) {
        posts.addPost(createNewPost(message));
    }

    private Post createNewPost(String message) {

        return new Post(parser.parse(message), getCurrentTime());
    }

    private SocialTime getCurrentTime() {
        return new SocialTime(now(clock));
    }

    public List<String> getOutput() {
        return output;
    }

}
