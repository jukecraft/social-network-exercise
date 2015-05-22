package org.twitterconsole.io;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class SocialNetworkingConsoleTest {
    private static final String OUTPUT = "I love the weather today";
    private static final String ANOTHER_OUTPUT = "Damn! We lost!";
    private static final String A_COMMAND = "Alice -> I love the weather today";

    @Rule
    public StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);
    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();

    @Test
    public void givenMultipleStringsItPrintsThemLineByLineToSystemOut() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();

        console.print(asList(OUTPUT, ANOTHER_OUTPUT));

        assertThat(log.getLog(), is(OUTPUT + lineSeparator() + ANOTHER_OUTPUT + lineSeparator()));
    }

    @Test
    public void givenAUserInputItReadsTheInputFromTheConsoleIntoACommand() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();
        input.provideText(A_COMMAND);

        Command command = console.getNextCommand();

        assertThat(command, is(new Command(A_COMMAND)));
    }

    @Test
    public void whenPrintingAPromptItPutsAnArrowAndSpaceToTheConsole() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();

        console.printPrompt();

        assertThat(log.getLog(), is("> "));
    }
}
