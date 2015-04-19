package accepting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MessageParserTest {

    @Test
    public void givenAnEmptyStringItReturnsAMessageObjectWithEmptyMessage() {
        MessageParser parser = new MessageParser();

        Message message = parser.parse("Alice -> ");

        assertThat(message.getMessageAsString(), is(""));
    }
}
