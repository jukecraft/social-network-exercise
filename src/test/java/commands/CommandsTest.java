package commands;

import static commands.CommandParameterBuilder.aPostCommand;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.builder.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Before;
import org.junit.Test;

import timeline.Output;
import timeline.PostsOutput;

public class CommandsTest {
    private static final String USERNAME = "Alice";
    private static final CommandParameter COMMAND_PARAMETER = aPostCommand().withUser(USERNAME).create();
    private static final PostsOutput OUTPUT = anEmptyPostsOutput().create();

    private Command applicableCommand = mock(Command.class);
    private Command notApplicableCommand = mock(Command.class);

    @Before
    public void setUpCommands() {
        when(applicableCommand.isApplicable(COMMAND_PARAMETER)).thenReturn(true);
        when(notApplicableCommand.isApplicable(COMMAND_PARAMETER)).thenReturn(false);
        when(applicableCommand.executeCommand(COMMAND_PARAMETER)).thenReturn(OUTPUT);
    }

    @Test
    public void givenMultipleCommandsTheFirstApplicableIsExecuted() {
        Commands commands = new Commands(asList(notApplicableCommand, applicableCommand));

        commands.execute(COMMAND_PARAMETER);

        verify(applicableCommand).executeCommand(COMMAND_PARAMETER);
        verify(notApplicableCommand, never()) //
            .executeCommand(any(CommandParameter.class));
    }

    @Test
    public void givenMultipleCommandsTheFirstApplicableIsExecutedAndTheResultIsCollected() {
        Commands commands = new Commands(asList(applicableCommand, applicableCommand));

        Output output = commands.execute(COMMAND_PARAMETER);

        assertThat(output, is(OUTPUT));
    }

}
