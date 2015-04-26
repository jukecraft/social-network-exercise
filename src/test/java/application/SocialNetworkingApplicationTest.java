package application;

import static application.ApplicationFactory.standardConfiguration;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.OutputBuilder.anOutput;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import timeline.Output;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final String COMMAND = "Alice -> I love the weather today";
    private static final Output OUTPUT = anOutput().create();
    private static final SocialTime TIME = aTime().create();
    private SocialNetworkingApplication socialNetworkingApplication;
    private Commands commands;

    @Before
    public void setUpApplication() {
        SocialTimeClock clock = mock(SocialTimeClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        commands = mock(Commands.class);
        socialNetworkingApplication = new SocialNetworkingApplication(standardConfiguration() //
            .withClock(clock) //
            .withCommands(commands));
    }

    @Test
    public void givenTheCommandsReturnAnOutputWhenTheApplicationAcceptsACommandThatOutputIsReturned() {
        when(commands.execute(new CommandParameter(COMMAND))).thenReturn(OUTPUT);

        socialNetworkingApplication.accept(COMMAND);

        assertThat(socialNetworkingApplication.getOutput(), is(OUTPUT.getOutput(TIME)));
    }

}
