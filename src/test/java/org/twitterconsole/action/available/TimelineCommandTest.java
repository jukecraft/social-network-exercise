package org.twitterconsole.action.available;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.UserBuilder.aUser;
import static org.twitterconsole.posts.output.PostsOutputBuilder.aPostsOutput;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.Output;
import org.twitterconsole.posts.output.PostsOutput;

public class TimelineCommandTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command TIMELINE_COMMAND = aCommand().withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = aPostsOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private TimelineAction action = new TimelineAction(timelineService);

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoTimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(new PostsOutput());

        Output output = action.executeWithOutput(TIMELINE_COMMAND);

        assertThat(output, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasATimelineForTheGivenUser() {
        when(timelineService.collectPosts(ALICE)).thenReturn(OUTPUT);

        Output output = action.executeWithOutput(TIMELINE_COMMAND);

        assertThat(output, is(OUTPUT));
    }

    @Test
    public void itIsExecutableIfItTheCommandIsEmpty() {
        assertThat(action.isExecutable(TIMELINE_COMMAND), is(true));
    }

    @Test
    public void itIsNotExecutableIfTheCommandIsNotEmpty() {
        Command command = aCommand() //
            .withCommand(" -> ") //
            .create();

        assertThat(action.isExecutable(command), is(false));
    }
}
