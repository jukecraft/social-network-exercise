package org.twitterconsole.action.output;

import static java.util.Optional.of;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.twitterconsole.io.CommandBuilder.aCommand;
import static org.twitterconsole.posts.output.WallOutputBuilder.aWallOutput;

import org.junit.Before;
import org.junit.Test;
import org.twitterconsole.io.Command;
import org.twitterconsole.posts.output.Output;

public class ObservableCommandTest {
    private static final Command PARAMETER = aCommand().create();
    private static final Output OUTPUT = aWallOutput().create();

    private ActionWithOutput actionWithOutput = mock(ActionWithOutput.class);
    private ActionObserver observer = mock(ActionObserver.class);

    @Before
    public void setUp() {
        when(actionWithOutput.executeWithOutput(PARAMETER)).thenReturn(of(OUTPUT));
    }

    @Test
    public void givenCreatedWithAnActionWhenItIsExecutedThenItCallsTheAction() {
        ObservableAction action = new ObservableAction(actionWithOutput);

        action.execute(PARAMETER);

        verify(actionWithOutput).executeWithOutput(PARAMETER);
    }

    @Test
    public void givenRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserverWithTheOutput() {
        ObservableAction action = new ObservableAction(actionWithOutput)
            .withObserver(observer);

        action.execute(PARAMETER);

        verify(observer).update(OUTPUT);
    }

    @Test
    public void givenTwoRegisteredObserversWhenItIsExecutedThenItNotifiesTheObserversWithTheOutput() {
        ActionObserver anotherObserver = mock(ActionObserver.class);
        ObservableAction action = new ObservableAction(actionWithOutput)
            .withObserver(observer)
            .withObserver(anotherObserver);

        action.execute(PARAMETER);

        verify(observer).update(OUTPUT);
        verify(anotherObserver).update(OUTPUT);
    }

}
