package application;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import accepting.MessageParser;
import accepting.Post;
import accepting.Posts;
import accepting.SocialTime;

public class SocialNetworkingApplication {
    private Posts posts = new Posts();
    private List<String> output = new ArrayList<>();
    private Clock clock;

    public SocialNetworkingApplication(Clock clock) {
        this.clock = clock;
    }

    public void accept(String message) {
        if (message.equals("Alice"))
            output.addAll(posts.printPosts(new SocialTime(LocalDateTime.now(clock))));
        else
            posts.addPost(new Post(new MessageParser().parse(message), new SocialTime(LocalDateTime.now(clock))));
    }

    public List<String> getOutput() {
        return output;
    }

}
