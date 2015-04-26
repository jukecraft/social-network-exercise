package commands;

import java.util.List;

import timeline.Output;

public class Commands {
    private List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public Output execute(CommandParameter commandParameter) {
        return commands.stream() //
            .filter(candidate -> candidate.isApplicable(commandParameter)) //
            .findFirst() //
            .map(command -> command.executeCommand(commandParameter)) //
            .orElse(new Output());
    }
}
