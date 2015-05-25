package org.twitterconsole.action.available;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.UserBuilder.aUser;
import static org.twitterconsole.posts.output.WallOutputBuilder.aWallOutput;
import static org.twitterconsole.posts.output.WallOutputBuilder.anEmptyWallOutput;

import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.UserRepository;
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class DisplayWallActionShould {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private PostRepository postRepository = mock(PostRepository.class);
    private UserRepository userRepository = mock(UserRepository.class);
    private ConsoleWithClock consoleWithClock = mock(ConsoleWithClock.class);

    private DisplayWallAction action = new DisplayWallAction(postRepository, userRepository, consoleWithClock);

    @Test
    public void executeIfCommandStartsWithWall() {
        when(userRepository.collectWallOutput(eq(postRepository), any(User.class)))
            .thenReturn(anEmptyWallOutput().create());

        Command command = aCommand().withCommand(" wall").create();

        action.execute(command);

        verify(userRepository).collectWallOutput(eq(postRepository), any(User.class));
    }

    @Test
    public void notExecuteIfCommandDoesntStartWithWall() {
        Command command = aCommand().withCommand(" -> is a wall").create();

        action.execute(command);

        verifyZeroInteractions(userRepository);
    }

    @Test
    public void returnNoOutputIfThereIsNoWallForTheGivenUser() {
        when(userRepository.collectWallOutput(postRepository, ALICE))
            .thenReturn(anEmptyWallOutput().create());

        action.execute(WALL_COMMAND);

        verify(consoleWithClock).print(anEmptyWallOutput().create());
    }

    @Test
    public void returnOutputOfWallForTheGivenUser() {
        when(userRepository.collectWallOutput(postRepository, ALICE))
            .thenReturn(OUTPUT);

        action.execute(WALL_COMMAND);

        verify(consoleWithClock).print(OUTPUT);
    }

}
