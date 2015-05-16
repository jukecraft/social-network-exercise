package commands;

import io.CommandParameter;

import java.util.List;

public class Commands {
    private List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public void execute(CommandParameter commandParameter) {
        commands.stream() //
            .filter(candidate -> candidate.isApplicable(commandParameter)) //
            .findFirst() //
            .ifPresent(command -> command.executeCommand(commandParameter));
    }
}
