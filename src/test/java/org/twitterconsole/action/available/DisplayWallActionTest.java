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
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.network.UsersPosts;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.WallOutput;

public class DisplayWallActionTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private UsersPosts usersPosts = mock(UsersPosts.class);
    private SocialNetwork network = mock(SocialNetwork.class);
    private ConsoleWithClock consoleWithClock = mock(ConsoleWithClock.class);

    private DisplayWallAction action = new DisplayWallAction(usersPosts, network, consoleWithClock);

    @Test
    public void itIsExecutableIfCommandStartsWithWall() {
        when(network.collectWallOutput(eq(usersPosts), any(User.class)))
            .thenReturn(anEmptyWallOutput().create());

        Command command = aCommand().withCommand(" wall").create();

        action.execute(command);

        verify(network).collectWallOutput(eq(usersPosts), any(User.class));
    }

    @Test
    public void itIsNotExecutableIfCommandDoesntStartWithWall() {
        Command command = aCommand().withCommand(" -> is a wall").create();

        action.execute(command);

        verifyZeroInteractions(network);
    }

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoWallForTheGivenUser() {
        when(network.collectWallOutput(usersPosts, ALICE))
            .thenReturn(anEmptyWallOutput().create());

        action.execute(WALL_COMMAND);

        verify(consoleWithClock).print(anEmptyWallOutput().create());
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasAWallForTheGivenUser() {
        when(network.collectWallOutput(usersPosts, ALICE))
            .thenReturn(OUTPUT);

        action.execute(WALL_COMMAND);

        verify(consoleWithClock).print(OUTPUT);
    }

}
