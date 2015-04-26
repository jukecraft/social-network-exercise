package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WallCommandTest {
    private WallCommand command = new WallCommand();

    @Test
    public void itIsApplicableIfCommandContainsWall() {
        CommandParameter commandParameter = aCommand().withCommand(" wall ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }
}
