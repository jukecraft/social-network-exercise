package application;

import static application.ApplicationFactory.standardConfiguration;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static timeline.SocialTimeBuilder.aTime;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final String COMMAND = "Alice -> I love the weather today";
    private static final List<String> OUTPUT = asList("Good game though. (1 minute ago)",
        "Damn! We lost! (2 minutes ago)");
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

        assertThat(socialNetworkingApplication.getOutput(), is(OUTPUT));
    }

}
