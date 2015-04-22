package accepting.builder;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

import accepting.Message;
import accepting.Post;
import accepting.SocialTime;

public class PostBuilder {

    private String messageText = "default message";
    private LocalDateTime timestamp = now();

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withMessage(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public PostBuilder withPostingTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Post create() {
        return new Post(new Message(messageText), new SocialTime(timestamp));
    }

}
