package application;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.SocialTimeBuilder.aTime;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import time.SocialTimeClock;
import timeline.Output;

public class ConsoleCommandObserverTest {
    private static final SocialTime TIME = aTime().create();
    private static final List<String> LINES = asList("Good game though. (1 minute ago)",
        "Damn! We lost! (2 minutes ago)");

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);
    private Output output = mock(Output.class);

    private CommandObserver outputObserver;

    @Before
    public void setUpApplication() {
        SocialTimeClock clock = mock(SocialTimeClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        outputObserver = new ConsoleCommandObserver(console, clock);
    }

    @Test
    public void givenTheCommandsReturnAnOutputWhenTheApplicationAcceptsACommandThatOutputIsReturned() {
        when(output.print(TIME)).thenReturn(LINES);

        outputObserver.update(output);

        verify(console).print(LINES);
    }
}
