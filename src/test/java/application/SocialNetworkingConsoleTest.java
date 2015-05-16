package application;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import commands.CommandParameter;

public class SocialNetworkingConsoleTest {
    private static final String OUTPUT = "I love the weather today";
    private static final String ANOTHER_OUTPUT = "Damn! We lost!";
    private static final String LINE_SEPERATOR = System.getProperty("line.separator");
    private static final String A_COMMAND = "Alice -> I love the weather today";

    @Rule
    public StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);
    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();

    @Test
    public void givenAStringItPrintsThatStringToSystemOut() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();

        console.print(OUTPUT);

        assertThat(log.getLog(), is(OUTPUT));
    }

    @Test
    public void givenMultipleStringsItPrintsThemLineByLineToSystemOut() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();

        console.print(asList(OUTPUT, ANOTHER_OUTPUT));

        assertThat(log.getLog(), is(OUTPUT + LINE_SEPERATOR + ANOTHER_OUTPUT + LINE_SEPERATOR));
    }

    @Test
    public void givenAUserInputItReadsTheInputFromTheConsole() {
        SocialNetworkingConsole console = new SocialNetworkingConsole();
        input.provideText(A_COMMAND);

        CommandParameter command = console.getNextCommand();

        assertThat(command, is(new CommandParameter(A_COMMAND)));
    }
}
