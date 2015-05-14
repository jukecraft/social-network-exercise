package application;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

public class SocialNetworkingConsoleTest {
    private static final String OUTPUT = "I love the weather today";
    private static final String ANOTHER_OUTPUT = "Damn! We lost!";
    private static final String LINE_SEPERATOR = System.getProperty("line.separator");

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);

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
}
