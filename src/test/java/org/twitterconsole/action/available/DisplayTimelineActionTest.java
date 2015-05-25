package org.twitterconsole.action.available;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.UserBuilder.aUser;
import static org.twitterconsole.posts.output.PostsOutputBuilder.aPostsOutput;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;
import org.twitterconsole.time.SocialNetworkingClock;

public class DisplayTimelineActionTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();
    private static final SocialTime TIME = aTime().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);

    private DisplayTimelineAction action = new DisplayTimelineAction(timelineService, clock, console);

    @Before
    public void setUp() {
        when(clock.getLocalDateTime()).thenReturn(TIME);
    }

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoTimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(new PostsOutput());

        action.execute(TIMELINE_COMMAND);

        verify(console).print(anEmptyPostsOutput().create().print(TIME));
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasATimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(OUTPUT);

        action.execute(TIMELINE_COMMAND);

        verify(console).print(OUTPUT.print(TIME));
    }

    @Test
    public void executesIfItTheCommandIsEmpty() {
        when(timelineService.collectPosts(any(User.class))).thenReturn(new PostsOutput());

        action.execute(TIMELINE_COMMAND);

        verify(timelineService).collectPosts(any(User.class));
    }

    @Test
    public void doesntExecuteIfTheCommandIsNotEmpty() {
        Command command = aCommand()
            .withCommand(" -> ")
            .create();

        action.execute(command);

        verifyZeroInteractions(timelineService);
    }
}
