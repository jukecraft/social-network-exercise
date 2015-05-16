package posts;

import static io.CommandBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.Command;

import org.junit.Test;

public class UserTest {
    @Test
    public void createdWithCommandParametersItPrintsToTheUserName() {
        Command parameter = aCommand().withUser("Alice").create();

        User user = new User(parameter);

        assertThat(user.toString(), is("Alice"));
    }
}
