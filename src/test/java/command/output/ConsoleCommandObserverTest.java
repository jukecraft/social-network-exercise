package command.output;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static posts.SocialTimeBuilder.aTime;
import io.SocialNetworkingConsole;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import posts.SocialTime;
import posts.output.Output;
import time.SocialNetworkingClock;
import actipon.output.ActionObserver;
import actipon.output.ConsoleObserver;

public class ConsoleCommandObserverTest {
    private static final SocialTime TIME = aTime().create();
    private static final List<String> LINES = asList("Good game though. (1 minute ago)",
        "Damn! We lost! (2 minutes ago)");

    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);
    private Output output = mock(Output.class);

    private ActionObserver consoleObserver;

    @Before
    public void setUpApplication() {
        SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
        when(clock.getLocalDateTime()).thenReturn(TIME);
        consoleObserver = new ConsoleObserver(console, clock);
    }

    @Test
    public void whenTheObserverIsUpdatedWithAnOutputTheConsolePrintsTheOutput() {
        when(output.print(TIME)).thenReturn(LINES);

        consoleObserver.update(output);

        verify(console).print(LINES);
    }
}
