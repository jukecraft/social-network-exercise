package application;

import static application.ApplicationFactory.standardConfiguration;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import timeline.Output;

import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final String COMMAND = "Alice -> I love the weather today";
    private static final Output OUTPUT = anEmptyOutput() //
        .withLine("Good game though. (1 minute ago)") //
        .withLine("Damn! We lost! (2 minutes ago)") //
        .create();
    private static final SocialTime TIME = aTime().create();
    private SocialNetworkingApplication socialNetworkingApplication;
    private Commands commands;

    @Before
    public void setUpApplication() {
        SocialTimeClock clock = mock(SocialTimeClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        commands = mock(Commands.class);
        socialNetworkingApplication = new SocialNetworkingApplication(standardConfiguration().withCommands(commands)
            .withClock(clock));
    }

    @Test
    public void givenTheCommandsReturnAnOutputWhenTheApplicationAcceptsACommandThatOutputIsReturned() {
        when(commands.execute(COMMAND, TIME)).thenReturn(OUTPUT);

        socialNetworkingApplication.accept(COMMAND);

        assertThat(socialNetworkingApplication.getOutput(), is(OUTPUT.getOutput()));
    }

}
