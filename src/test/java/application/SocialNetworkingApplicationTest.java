package application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.SocialTimeBuilder.aTime;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;

import commands.CommandParameter;
import commands.Commands;

public class SocialNetworkingApplicationTest {
    private static final String COMMAND = "Alice -> I love the weather today";
    private static final SocialTime TIME = aTime().create();

    private SocialNetworkingApplication socialNetworkingApplication;
    private Commands commands;

    @Before
    public void setUpApplication() {
        SocialTimeClock clock = mock(SocialTimeClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        commands = mock(Commands.class);
        socialNetworkingApplication = new SocialNetworkingApplication(commands);
    }

    @Test
    public void whenTheApplicationAcceptsACommandThatCommandIsRoutedToCommands() {
        socialNetworkingApplication.accept(COMMAND);

        verify(commands).execute(new CommandParameter(COMMAND));
    }
}
