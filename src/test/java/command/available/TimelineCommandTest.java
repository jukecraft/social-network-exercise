package command.available;

import static io.CommandBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static posts.UserBuilder.aUser;
import static posts.output.PostsOutputBuilder.aPostsOutput;
import static posts.output.PostsOutputBuilder.anEmptyPostsOutput;
import io.Command;
import network.TimelineService;

import org.junit.Test;

import posts.User;
import posts.output.Output;
import posts.output.PostsOutput;
import action.available.TimelineAction;

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
