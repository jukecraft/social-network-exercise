package org.twitterconsole.action.available;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
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
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.Output;
import org.twitterconsole.posts.output.WallOutput;

public class WallActionTest {
    private static final String USERNAME = "Alice";
    private static final User ALICE = aUser().withName(USERNAME).create();
    private static final Command WALL_COMMAND = aCommand().withCommand(" wall").withUser(USERNAME).create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private TimelineService timelineService = mock(TimelineService.class);
    private WallAction action = new WallAction(timelineService);

    @Test
    public void itIsExecutableIfCommandStartsWithWall() {
        when(timelineService.collectWall(any(User.class))).thenReturn(anEmptyWallOutput().create());

        Command command = aCommand().withCommand(" wall").create();

        action.execute(command);

        verify(timelineService).collectWall(any(User.class));

    }

    @Test
    public void itIsNotExecutableIfCommandDoesntStartWithWall() {
        Command command = aCommand().withCommand(" -> is a wall").create();

        action.execute(command);

        verifyZeroInteractions(timelineService);
    }

    @Test
    public void itReturnsNoOutputIfTimelineServiceHasNoWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(anEmptyWallOutput().create());

        Output output = action.executeWithOutput(WALL_COMMAND).get();

        assertThat(output, is(anEmptyWallOutput().create()));
    }

    @Test
    public void itReturnsOutputIfTimelineServiceHasAWallForTheGivenUser() {
        when(timelineService.collectWall(ALICE)).thenReturn(OUTPUT);

        Output output = action.executeWithOutput(WALL_COMMAND).get();

        assertThat(output, is(OUTPUT));
    }

}