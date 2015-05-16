package command.available;

import io.CommandParameter;
import network.TimelineService;
import posts.User;

import command.Command;

public class FollowCommand implements Command {
    private static final String COMMAND_IDENTIFIER = " follows ";

    private TimelineService timelineService;

    public FollowCommand(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(COMMAND_IDENTIFIER);
    }

    @Override
    public void executeCommand(CommandParameter parameter) {
        timelineService.registerFollowing(new User(parameter), extractSecondUser(parameter));
    }

    private User extractSecondUser(CommandParameter parameter) {
        CommandParameter secondUserParameter = new CommandParameter(parameter.afterIdentifier(COMMAND_IDENTIFIER));
        return new User(secondUserParameter);
    }

}
