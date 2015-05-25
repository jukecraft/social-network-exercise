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
import org.twitterconsole.network.PostRepository;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class DisplayTimelineActionShould {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();

    private PostRepository postRepository = mock(PostRepository.class);
    private ConsoleWithClock consoleWithClock = mock(ConsoleWithClock.class);

    private DisplayTimelineAction action = new DisplayTimelineAction(postRepository, consoleWithClock);

    @Test
    public void returnNoOutputIfThereAreNoPostsForTheGivenUser() {
        when(postRepository.collectPosts(ALICE)).thenReturn(new PostsOutput());

        action.execute(TIMELINE_COMMAND);

        verify(consoleWithClock).print(anEmptyPostsOutput().create());
    }

    @Test
    public void returnOutputWithPostsForTheGivenUser() {
        when(postRepository.collectPosts(ALICE)).thenReturn(OUTPUT);

        action.execute(TIMELINE_COMMAND);

        verify(consoleWithClock).print(OUTPUT);
    }

    @Test
    public void executeIfTheCommandIsEmpty() {
        when(postRepository.collectPosts(any(User.class))).thenReturn(new PostsOutput());

        action.execute(TIMELINE_COMMAND);

        verify(postRepository).collectPosts(any(User.class));
    }

    @Test
    public void notExecuteIfTheCommandIsNotEmpty() {
        Command command = aCommand()
            .withCommand(" -> ")
            .create();

        action.execute(command);

        verifyZeroInteractions(postRepository);
    }
}
