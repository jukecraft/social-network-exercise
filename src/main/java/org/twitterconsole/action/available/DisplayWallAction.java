package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.network.UsersPosts;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class DisplayWallAction implements Action {
    private static final String WALL_IDENTIFIER = " wall";

    private ConsoleWithClock consoleWithClock;
    private UsersPosts usersPosts;
    private SocialNetwork network;

    public DisplayWallAction(UsersPosts usersPosts, SocialNetwork network, ConsoleWithClock consoleWithClock) {
        this.usersPosts = usersPosts;
        this.network = network;
        this.consoleWithClock = consoleWithClock;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command)) {
            printWall(command);
        }
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(WALL_IDENTIFIER);
    }

    private void printWall(Command command) {
        WallOutput wallOutput = collectWall(new User(command));
        consoleWithClock.print(wallOutput);
    }

    private WallOutput collectWall(User user) {
        return network.collectWallOutput(usersPosts, user);
    }

}
