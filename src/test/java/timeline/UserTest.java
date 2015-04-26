package timeline;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import commands.CommandParameter;

public class UserTest {
    @Test
    public void createdWithCommandParametersItPrintsToTheUserName() {
        CommandParameter parameter = aCommand().withUser("USER").create();

        User user = new User(parameter);

        assertThat(user.toString(), is("USER"));
    }
}
