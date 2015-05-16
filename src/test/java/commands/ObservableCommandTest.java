package commands;

import static commands.CommandParameterBuilder.aCommand;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.WallOutputBuilder.aWallOutput;

import org.junit.Before;
import org.junit.Test;

import timeline.Output;
import application.CommandObserver;

public class ObservableCommandTest {
    private static final CommandParameter PARAMETER = aCommand().create();
    private static final Output OUTPUT = aWallOutput().create();

    private CommandWithOutput command = mock(CommandWithOutput.class);
    private ObservableCommand observableCommand = new ObservableCommand(command);
    private CommandObserver observer = mock(CommandObserver.class);

    @Before
    public void setUp() {
        when(command.executeCommand(PARAMETER)).thenReturn(OUTPUT);
        observableCommand.registerObserver(observer);
    }

    @Test
    public void givenCreatedWithACommandWhenItIsExecutedThenItCallsTheGivenCommand() {
        observableCommand.executeCommand(PARAMETER);

        verify(command).executeCommand(PARAMETER);
    }

    @Test
    public void givenRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserverWithTheOutput() {
        observableCommand.executeCommand(PARAMETER);

        verify(observer).update(OUTPUT);
    }

    @Test
    public void givenTwoRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserversWithTheOutput() {
        CommandObserver anotherObserver = mock(CommandObserver.class);
        observableCommand.registerObserver(anotherObserver);

        observableCommand.executeCommand(PARAMETER);

        verify(observer).update(OUTPUT);
        verify(anotherObserver).update(OUTPUT);
    }

    @Test
    public void givenCommandIsApplicableItIsApplicable() {
        when(command.isApplicable(PARAMETER)).thenReturn(true);

        assertThat(observableCommand.isApplicable(PARAMETER), is(true));
    }

    @Test
    public void givenCommandIsNotApplicableItIsNotApplicable() {
        when(command.isApplicable(PARAMETER)).thenReturn(false);

        assertThat(observableCommand.isApplicable(PARAMETER), is(false));
    }
}
