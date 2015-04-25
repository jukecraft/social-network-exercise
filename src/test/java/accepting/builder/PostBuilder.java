package accepting.builder;

import static accepting.builder.SocialTimeBuilder.aTime;

import java.time.LocalDateTime;

import accepting.Message;
import accepting.Post;
import accepting.SocialTime;

public class PostBuilder {

    private String messageText = "default message";
    private SocialTime timestamp = aTime().create();

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withMessage(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public PostBuilder withPostingTime(LocalDateTime timestamp) {
        this.timestamp = aTime().withTimestamp(timestamp).create();
        return this;
    }

    public PostBuilder withPostingTime(SocialTimeBuilder timestamp) {
        this.timestamp = timestamp.create();
        return this;
    }

    public Post create() {
        return new Post(new Message("-> " + messageText), timestamp);
    }
}
