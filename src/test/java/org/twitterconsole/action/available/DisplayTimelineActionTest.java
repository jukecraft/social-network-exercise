package org.twitterconsole.action.available;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.UserBuilder.aUser;
import static org.twitterconsole.posts.output.PostsOutputBuilder.aPostsOutput;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class DisplayTimelineActionTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private ConsoleWithClock consoleWithClock = mock(ConsoleWithClock.class);

    private DisplayTimelineAction action = new DisplayTimelineAction(timelineService, consoleWithClock);

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoTimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(new PostsOutput());

        action.execute(TIMELINE_COMMAND);

        verify(consoleWithClock).print(anEmptyPostsOutput().create());
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasATimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(OUTPUT);

        action.execute(TIMELINE_COMMAND);

        verify(consoleWithClock).print(OUTPUT);
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
