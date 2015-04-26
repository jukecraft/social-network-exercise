package timeline;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import commands.CommandParameter;

public class MessageTest {

    @Test
    public void givenAStringWithAMessageItReturnsAMessageObjectWithThatString() {
        Message message = new Message(new CommandParameter("Alice -> I love the weather today"));

        assertThat(message.toString(), is("I love the weather today"));
    }

}
