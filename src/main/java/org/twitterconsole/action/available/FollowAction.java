package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.UserRepository;
import org.twitterconsole.posts.User;

public class FollowAction implements Action {
    private static final String FOLLOW_IDENTIFIER = " follows ";

    private UserRepository userRepository;

    public FollowAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command))
            userRepository.registerFollowing(new User(command), extractSecondUser(command));
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(FOLLOW_IDENTIFIER);
    }

    private User extractSecondUser(Command command) {
        Command secondUserParameter = new Command(command.afterIdentifier(FOLLOW_IDENTIFIER));
        return new User(secondUserParameter);
    }

}
