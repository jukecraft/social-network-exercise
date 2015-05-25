package org.twitterconsole.action.available;

import org.twitterconsole.action.Action;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.UserRepository;
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class DisplayWallAction implements Action {
    private static final String WALL_IDENTIFIER = " wall";

    private ConsoleWithClock consoleWithClock;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public DisplayWallAction(PostRepository postRepository, UserRepository userRepository, ConsoleWithClock consoleWithClock) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
        return userRepository.collectWallOutput(postRepository, user);
    }

}
