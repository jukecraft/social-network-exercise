package accepting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageParserTest {

    private MessageParser parser = new MessageParser();;

    @Test
    public void givenAnEmptyStringItReturnsAnEmptyMessageObject() {
        Message message = parser.parse("");

        assertThat(message.toString(), is(""));
    }

    @Test
    public void givenAnEmptySecondMessagePartItReturnsAnEmptyMessageObject() {
        Message message = parser.parse("Alice ->");

        assertThat(message.toString(), is(""));
    }

    @Test
    public void givenAStringWithAMessageItReturnsAMessageObjectWithThatString() {
        Message message = parser.parse("Alice -> I love the weather today");

        assertThat(message.toString(), is("I love the weather today"));
    }

}
