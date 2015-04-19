package accepting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageParserTest {

    @Test
    public void givenAnEmptyStringItReturnsAnEmptyMessageObject() {
        MessageParser parser = new MessageParser();

        Message message = parser.parse("");

        assertThat(message.toString(), is(""));
    }

    @Test
    public void givenAStringWithAMessageItReturnsAMessageObjectWithThatString() {
        MessageParser parser = new MessageParser();

        Message message = parser.parse("Alice -> I love the weather today");

        assertThat(message.toString(), is("I love the weather today"));
    }

}
