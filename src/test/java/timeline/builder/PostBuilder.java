package timeline.builder;

import static commands.CommandParameterBuilder.aCommand;
import static timeline.builder.SocialTimeBuilder.aTime;
import static timeline.builder.UserBuilder.aUser;

import java.time.LocalDateTime;

import time.SocialTime;
import timeline.Message;
import timeline.Post;
import timeline.User;

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
        return new Post(new Message(aCommand().withMessage(messageText).create()), timestamp, user);
    }

}
