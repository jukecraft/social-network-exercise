package application;

import static java.lang.System.setOut;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class SocialNetworkingConsoleTest {

    private static final String OUTPUT = "I love the weather today";

    @Test
    public void givenAStringItPrintsThatStringToSystemOut() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        setOut(new PrintStream(actualOutput));
        SocialNetworkingConsole console = new SocialNetworkingConsole();

        console.print(OUTPUT);

        assertThat(actualOutput.toString(), is(OUTPUT));

    }
}
