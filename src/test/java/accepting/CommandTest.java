package accepting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CommandTest {

    @Test
    public void whenItParsesACommandFromStringItExtractsTheUser() {
        Command command = new Command("Alice -> I love the weather today");

        assertThat(command.getUser(), is("Alice"));
    }

    @Test
    public void whenItParsesACommandFromAnotherUserItExtractsTheUser() {
        Command command = new Command("Bob -> Good game though.");

        assertThat(command.getUser(), is("Bob"));
    }

}
