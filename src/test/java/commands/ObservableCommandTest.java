package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class ObservableCommandTest {
    private static final CommandParameter PARAMETER = aCommand().create();

    @Test
    public void givenCreatedWithACommandWhenItIsExecutedThenItCallsTheGivenCommand() {
        Command command = mock(Command.class);
        ObservableCommand observableCommand = new ObservableCommand(command);

        observableCommand.executeCommand(PARAMETER);

        verify(command).executeCommand(PARAMETER);
    }
}
