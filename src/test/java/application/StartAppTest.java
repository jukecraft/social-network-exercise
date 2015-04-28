package application;

import static java.lang.System.setOut;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class StartAppTest {
    @Test
    public void whenIStartTheAppItGreetsTheUser() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        setOut(new PrintStream(actualOutput));

        StartApp.main();

        assertEquals("Welcome to my social network application", actualOutput.toString());
    }
}
