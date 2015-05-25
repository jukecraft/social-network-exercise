package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.posts.User;

public class FollowAction implements Action {
    private static final String FOLLOW_IDENTIFIER = " follows ";

    private SocialNetwork network;

    public FollowAction(SocialNetwork network) {
        this.network = network;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command))
            network.registerFollowing(new User(command), extractSecondUser(command));
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(FOLLOW_IDENTIFIER);
    }

    private User extractSecondUser(Command command) {
        Command secondUserParameter = new Command(command.afterIdentifier(FOLLOW_IDENTIFIER));
        return new User(secondUserParameter);
    }

}
