package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.posts.Message;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.User;
import org.twitterconsole.time.SocialNetworkingClock;

public class PostAction implements Action {
    private static final String POST_IDENTIFIER = " -> ";

    private SocialNetworkingClock clock;
    private PostRepository postRepository;

    public PostAction(PostRepository postRepository, SocialNetworkingClock clock) {
        this.postRepository = postRepository;
        this.clock = clock;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command)) {
            Post post = createPost(command);
            postRepository.post(post);
        }
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(POST_IDENTIFIER);
    }

    private Post createPost(Command command) {
        String message = command.afterIdentifier(POST_IDENTIFIER);
        User author = new User(command);
        return new Post(author, new Message(message), clock.getLocalDateTime());
    }

}
