package accepting;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CommandTest {

    @Test
    public void whenItParsesAStringItExtractsTheUser() {
        Command command = new Command("Alice -> I love the weather today");

        assertThat(command.getUser(), is("Alice"));
    }

}
