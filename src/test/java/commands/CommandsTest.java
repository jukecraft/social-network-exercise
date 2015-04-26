package commands;

import static commands.UserBuilder.aUser;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Before;
import org.junit.Test;

import time.SocialTime;
import timeline.Output;

public class CommandsTest {
    private static final String USERNAME = "Alice";
    private static final User EXPECTED_USER = aUser().withName(USERNAME).create();
    private static final String COMMAND = " test command";
    private static final SocialTime TIME = aTime().create();
    private static final Output OUTPUT = anEmptyOutput().create();

    private Command applicableCommand = mock(Command.class);
    private Command notApplicableCommand = mock(Command.class);

    @Before
    public void setUpCommands() {
        when(applicableCommand.isApplicable(COMMAND)).thenReturn(true);
        when(notApplicableCommand.isApplicable(COMMAND)).thenReturn(false);
        when(applicableCommand.executeCommand(EXPECTED_USER, COMMAND, TIME)).thenReturn(OUTPUT);
    }

    @Test
    public void givenMultipleCommandsTheFirstApplicableIsExecuted() {
        Commands commands = new Commands(asList(notApplicableCommand, applicableCommand));

        commands.execute(USERNAME + COMMAND, TIME);

        verify(applicableCommand).executeCommand(EXPECTED_USER, COMMAND, TIME);
        verify(notApplicableCommand, never()).executeCommand(any(User.class), any(String.class), any(SocialTime.class));
    }

    @Test
    public void givenMultipleCommandsTheFirstApplicableIsExecutedAndTheResultIsCollected() {
        Commands commands = new Commands(asList(applicableCommand, applicableCommand));

        Output output = commands.execute(USERNAME + COMMAND, TIME);

        assertThat(output, is(OUTPUT));
    }

}
