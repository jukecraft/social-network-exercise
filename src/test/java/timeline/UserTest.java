package timeline;

import static command.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.CommandParameter;

import org.junit.Test;

public class UserTest {
    @Test
    public void createdWithCommandParametersItPrintsToTheUserName() {
        CommandParameter parameter = aCommand().withUser("USER").create();

        User user = new User(parameter);

        assertThat(user.toString(), is("USER"));
    }
}
