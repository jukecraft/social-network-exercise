package org.twitterconsole.action.available;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.output.WallOutputBuilder.aWallOutput;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.io.SocialNetworkingConsole;
import org.twitterconsole.posts.SocialTime;
import org.twitterconsole.posts.output.WallOutput;
import org.twitterconsole.time.SocialNetworkingClock;

public class ConsoleWithClockTest {
    private static final SocialTime TIME = aTime().create();
    private static final WallOutput OUTPUT = aWallOutput().create();

    private SocialNetworkingClock clock = mock(SocialNetworkingClock.class);
    private SocialNetworkingConsole console = mock(SocialNetworkingConsole.class);

    @Before
    public void setUp() {
        when(clock.getLocalDateTime()).thenReturn(TIME);
    }

    @Test
    public void itIsExecutableIfCommandStartsWithWall() {
        ConsoleWithClock consoleWithClock = new ConsoleWithClock(console, clock);

        consoleWithClock.print(OUTPUT);

        verify(console).print(OUTPUT.print(TIME));

    }
}
