package commands;


public interface Command {
    boolean isApplicable(CommandParameter commandParameter);

    void executeCommand(CommandParameter commandParameter);
}
