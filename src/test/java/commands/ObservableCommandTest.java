package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.WallOutputBuilder.aWallOutput;

import org.junit.Test;

import timeline.Output;
import application.CommandObserver;

public class ObservableCommandTest {
    private static final CommandParameter PARAMETER = aCommand().create();
    private static final Output OUTPUT = aWallOutput().create();

    @Test
    public void givenCreatedWithACommandWhenItIsExecutedThenItCallsTheGivenCommand() {
        Command command = mock(Command.class);
        when(command.executeCommand(PARAMETER)).thenReturn(OUTPUT);
        ObservableCommand observableCommand = new ObservableCommand(command);
        CommandObserver observer = mock(CommandObserver.class);
        observableCommand.registerObserver(observer);

        observableCommand.executeCommand(PARAMETER);

        verify(command).executeCommand(PARAMETER);
    }

    @Test
    public void givenRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserverWithTheOutput() {
        Command command = mock(Command.class);
        when(command.executeCommand(PARAMETER)).thenReturn(OUTPUT);
        ObservableCommand observableCommand = new ObservableCommand(command);
        CommandObserver observer = mock(CommandObserver.class);
        observableCommand.registerObserver(observer);

        observableCommand.executeCommand(PARAMETER);

        verify(observer).update(OUTPUT);
    }
}
