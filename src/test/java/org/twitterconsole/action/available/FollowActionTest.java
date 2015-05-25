package org.twitterconsole.action.available;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.UserBuilder.aUser;

import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.SocialNetwork;
import org.twitterconsole.posts.User;

public class FollowActionTest {
    private static final String USERNAME_ALICE = "Alice";
    private static final User ALICE = aUser().withName(USERNAME_ALICE).create();
    private static final String USERNAME_BOB = "Bob";
    private static final User BOB = aUser().withName(USERNAME_BOB).create();

    private SocialNetwork network = mock(SocialNetwork.class);
    private FollowAction action = new FollowAction(network);

    @Test
    public void executesIfCommandStartsWithFollows() {
        Command command = aCommand().withCommand(" follows Bob").create();

        action.execute(command);

        verify(network).registerFollowing(any(User.class), any(User.class));
    }

    @Test
    public void doesntExecuteIfCommandDoesntStartWithFollows() {
        Command command = aCommand().withCommand(" -> is as follows ").create();

        action.execute(command);

        verifyZeroInteractions(network);
    }

    @Test
    public void itRegisteresWithTimelinesThatAliceIsFollowingBob() {
        action.execute(aCommand()
            .withUser(USERNAME_ALICE)
            .withCommand(" follows " + USERNAME_BOB)
            .create());

        verify(network).registerFollowing(ALICE, BOB);
    }

    @Test
    public void itRegisteresWithTimelinesThatBobIsFollowingAlice() {
        action.execute(aCommand()
            .withUser(USERNAME_BOB)
            .withCommand(" follows " + USERNAME_ALICE)
            .create());

        verify(network).registerFollowing(BOB, ALICE);
    }

}
