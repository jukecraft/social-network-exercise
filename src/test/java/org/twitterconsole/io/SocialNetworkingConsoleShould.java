package org.twitterconsole.io;

import static java.lang.System.lineSeparator;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class SocialNetworkingConsoleShould {
    private static final String OUTPUT = "I love the weather today";
    private static final String ANOTHER_OUTPUT = "Damn! We lost!";
    private static final String A_COMMAND = "Alice -> I love the weather today";

    @Rule
    public StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);
    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();

    private SocialNetworkingConsole console = new SocialNetworkingConsole();

    @Test
    public void printMultipleStringsToSystemOut() {
        console.print(asList(OUTPUT, ANOTHER_OUTPUT));

        assertThat(log.getLog(), is(OUTPUT + lineSeparator() + ANOTHER_OUTPUT + lineSeparator()));
    }

    @Test
    public void readInputFromTheConsole() {
        input.provideText(A_COMMAND);

        Optional<Command> command = console.getNextCommand();

        assertThat(command.get(), is(new Command(A_COMMAND)));
    }

    @Test
    public void printAPromptToTheConsole() {
        console.printPrompt();

        assertThat(log.getLog(), is("> "));
    }
}
