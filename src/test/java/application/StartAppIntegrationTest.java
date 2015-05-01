package application;

import static java.lang.System.setOut;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class StartAppIntegrationTest {
    @Rule
    public TextFromStandardInputStream input = emptyStandardInputStream();

    @Test
    public void whenIStartTheAppItGreetsTheUser() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        setOut(new PrintStream(actualOutput));

        StartApp.main();

        assertThat(actualOutput.toString(), containsString("Welcome to my social network application"));
    }

    @Test
    public void whenAUserPostsAndChecksTheirTimelineItPrintsThePost() {
        String firstPost = "Alice -> I love the weather today";
        String timelineCommand = "Alice";
        input.provideText(firstPost, timelineCommand);

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        setOut(new PrintStream(actualOutput));

        StartApp.main();

        assertThat(actualOutput.toString(), containsString("I love the weather today ("));
    }

}
