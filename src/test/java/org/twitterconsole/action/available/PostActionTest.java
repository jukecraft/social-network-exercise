package org.twitterconsole.action.available;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.UsersPosts;
import org.twitterconsole.posts.Message;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;
import org.twitterconsole.time.SocialNetworkingClock;

public class PostActionTest {
    private static final User ALICE = aUserNamedAlice();
    private static final SocialTime TIME = aTime().create();

    private UsersPosts usersPosts = mock(UsersPosts.class);
    private SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
    private PostAction action = new PostAction(usersPosts, clock);

    @Before
    public void setUp() {
        when(clock.getLocalDateTime()).thenReturn(TIME);
    }

    @Test
    public void itPostsANewMessageInTheTimelinesForTheGivenUser() {
        Command command = aCommand().withCommand(" -> I love the weather today").create();

        action.execute(command);

        verify(usersPosts).post(new Post(ALICE, new Message("I love the weather today"), TIME));
    }

    @Test
    public void itPostsADifferentNewMessageInTheTimelinesForTheGivenUser() {
        Command command = aCommand().withCommand(" -> Good game though.").create();

        action.execute(command);

        verify(usersPosts).post(new Post(ALICE, new Message("Good game though."), TIME));
    }

    @Test
    public void executesIfCommandStartsWithAnArrow() {
        Command command = aCommand().withCommand(" -> Bob").create();

        action.execute(command);

        verify(usersPosts).post(any(Post.class));
    }

    @Test
    public void doesntExecuteIfCommandDoesntStartWithAnArrow() {
        Command command = aCommand().withCommand(" follows ->").create();

        action.execute(command);

        verifyZeroInteractions(usersPosts);
    }

}
