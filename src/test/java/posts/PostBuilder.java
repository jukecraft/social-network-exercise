package posts;

import static io.CommandBuilder.aCommand;
import static posts.SocialTimeBuilder.aTime;
import static posts.UserBuilder.aUser;

import java.time.LocalDateTime;

public class PostBuilder {

    private String messageText = "default message";
    private SocialTime timestamp = aTime().create();
    private User user = aUser().create();

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withMessage(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public PostBuilder withPostingTime(SocialTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public PostBuilder withPostingTime(SocialTimeBuilder timestampBuilder) {
        return withPostingTime(timestampBuilder.create());
    }

    public PostBuilder withPostingTime(LocalDateTime timestamp) {
        return withPostingTime(aTime().withTimestamp(timestamp));
    }

    public PostBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Post create() {
        return new Post(user, new Message(aCommand().withMessage(messageText).create()), timestamp);
    }

    public static Post onePost() {
        return aPost().withMessage("one post").create();
    }

    public static Post anotherPost() {
        return aPost().withMessage("another post").create();
    }

}
