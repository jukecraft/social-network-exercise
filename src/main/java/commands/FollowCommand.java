package commands;

public class FollowCommand {

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.contains(" follows ");
    }

}
