package commands;

import static accepting.UserBuilder.aUser;
import static accepting.builder.SocialTimeBuilder.aTime;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import accepting.SocialTime;
import accepting.User;

public class CommandsTest {
    private static final String USERNAME = "Alice";
    private static final String COMMAND = "test command";
    private static final SocialTime TIME = aTime().create();

    private Command applicableCommand = mock(Command.class);
    private Command notApplicableCommand = mock(Command.class);

    @Before
    public void setUpCommands() {
        when(applicableCommand.isApplicable(COMMAND)).thenReturn(true);
        when(notApplicableCommand.isApplicable(COMMAND)).thenReturn(false);
    }

    @Test
    public void givenMultipleCommandsWhenTheyAreExecutedThenOnlyTheApplicableAreExecutedWithTheExtractedUser() {
        Commands commands = new Commands(asList(applicableCommand, notApplicableCommand));
        User expectedUser = aUser().withName(USERNAME).create();

        commands.execute(USERNAME + COMMAND, TIME);

        verify(applicableCommand).executeCommand(expectedUser, COMMAND, TIME);
        verify(notApplicableCommand, never()).executeCommand(any(User.class), any(String.class), any(SocialTime.class));
    }

}
