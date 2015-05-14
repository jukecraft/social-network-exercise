package application;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.LogMode.LOG_ONLY;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class StartAppIntegrationTest {
    private static final String[] IRRELEVANT_PARAMETERS = null;

    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog(LOG_ONLY);

    @Test
    public void whenIStartTheAppItGreetsTheUser() {
        StartApp.main(IRRELEVANT_PARAMETERS);

        assertThat(log.getLog(), containsString("Welcome to my social network application"));
    }

    @Test
    @Ignore
    public void whenAUserPostsAndChecksTheirTimelineItPrintsThePost() {
        String firstPost = "Alice -> I love the weather today";
        String timelineCommand = "Alice";
        input.provideText(firstPost, timelineCommand);

        StartApp.main(IRRELEVANT_PARAMETERS);

        assertThat(log.getLog(), containsString("I love the weather today ("));
    }

}
