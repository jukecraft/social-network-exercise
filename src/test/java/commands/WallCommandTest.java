package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WallCommandTest {
    private WallCommand command = new WallCommand();

    @Test
    public void itIsApplicableIfCommandStartsWithWall() {
        CommandParameter commandParameter = aCommand().withCommand(" wall ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(true));
    }

    @Test
    public void itIsNotApplicableIfCommandDoesntStartWithWall() {
        CommandParameter commandParameter = aCommand().withCommand(" -> is a wall ").create();

        boolean isApplicable = command.isApplicable(commandParameter);

        assertThat(isApplicable, is(false));
    }
}
