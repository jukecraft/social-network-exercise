package posts;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.Command;

import org.junit.Test;

public class MessageTest {

    @Test
    public void givenACommandWithAMessageItReturnsAMessageObjectWithThatString() {
        Message message = new Message(new Command("Alice -> I love the weather today"));

        assertThat(message.toString(), is("I love the weather today"));
    }

}
