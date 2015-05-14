package application;

import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.concurrent.TimeUnit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.Timeout;

public class StartAppIntegrationTest {
    private static final String[] IRRELEVANT_PARAMETERS = null;

    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();
    @Rule
    public StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);

    @Rule
    public Timeout timeout = new Timeout(5, TimeUnit.SECONDS);

    @Test
    public void whenIStartTheAppItGreetsTheUser() {
        input.provideText("" + lineSeparator());

        StartApp.main(IRRELEVANT_PARAMETERS);

        assertThat(log.getLog(), containsString("Welcome to my social network application"));
    }

    @Test
    public void whenAUserPostsAndChecksTheirTimelineItPrintsThePost() {
        String firstPost = "Alice -> I love the weather today";
        String timelineCommand = "Alice";
        input.provideText(firstPost + lineSeparator(), timelineCommand + lineSeparator(), "" + lineSeparator());

        StartApp.main(IRRELEVANT_PARAMETERS);

        assertThat(log.getLog(), containsString("I love the weather today ("));
    }

}
