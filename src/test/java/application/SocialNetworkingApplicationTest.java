package application;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.builder.PostsOutputBuilder.aPostsOutput;
import static timeline.builder.SocialTimeBuilder.aTime;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import timeline.PostsOutput;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final String COMMAND = "Alice -> I love the weather today";
    private static final PostsOutput OUTPUT = aPostsOutput().create();
    private static final SocialTime TIME = aTime().create();
    private SocialNetworkingApplication socialNetworkingApplication;
    private Commands commands;

    @Before
    public void setUpApplication() {
        SocialTimeClock clock = mock(SocialTimeClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        commands = mock(Commands.class);
        socialNetworkingApplication = new SocialNetworkingApplication(clock, commands);
    }

    @Test
    public void givenTheCommandsReturnAnOutputWhenTheApplicationAcceptsACommandThatOutputIsReturned() {
        when(commands.execute(new CommandParameter(COMMAND))).thenReturn(OUTPUT);

        socialNetworkingApplication.accept(COMMAND);

        assertThat(socialNetworkingApplication.getOutput(), is(OUTPUT.print(TIME)));
    }

}
