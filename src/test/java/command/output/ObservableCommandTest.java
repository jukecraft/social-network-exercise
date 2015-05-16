package command.output;

import static io.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static posts.output.WallOutputBuilder.aWallOutput;
import io.CommandParameter;

import org.junit.Before;
import org.junit.Test;

import posts.output.Output;

public class ObservableCommandTest {
    private static final CommandParameter PARAMETER = aCommand().create();
    private static final Output OUTPUT = aWallOutput().create();

    private CommandWithOutput command = mock(CommandWithOutput.class);
    private CommandObserver observer = mock(CommandObserver.class);

    @Before
    public void setUp() {
        when(command.executeCommandWithOutput(PARAMETER)).thenReturn(OUTPUT);
    }

    @Test
    public void givenCreatedWithACommandWhenItIsExecutedThenItCallsTheGivenCommand() {
        ObservableCommand observableCommand = new ObservableCommand(command);

        observableCommand.executeCommand(PARAMETER);

        verify(command).executeCommandWithOutput(PARAMETER);
    }

    @Test
    public void givenRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserverWithTheOutput() {
        ObservableCommand observableCommand = new ObservableCommand(command) //
            .withObserver(observer);

        observableCommand.executeCommand(PARAMETER);

        verify(observer).update(OUTPUT);
    }

    @Test
    public void givenTwoRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserversWithTheOutput() {
        CommandObserver anotherObserver = mock(CommandObserver.class);
        ObservableCommand observableCommand = new ObservableCommand(command) //
            .withObserver(observer) //
            .withObserver(anotherObserver);

        observableCommand.executeCommand(PARAMETER);

        verify(observer).update(OUTPUT);
        verify(anotherObserver).update(OUTPUT);
    }

    @Test
    public void givenCommandIsApplicableItIsApplicable() {
        ObservableCommand observableCommand = new ObservableCommand(command);
        when(command.isApplicable(PARAMETER)).thenReturn(true);

        assertThat(observableCommand.isApplicable(PARAMETER), is(true));
    }

    @Test
    public void givenCommandIsNotApplicableItIsNotApplicable() {
        ObservableCommand observableCommand = new ObservableCommand(command);
        when(command.isApplicable(PARAMETER)).thenReturn(false);

        assertThat(observableCommand.isApplicable(PARAMETER), is(false));
    }
}
