package commands;

public class WallCommand {

    private static final String WALL_IDENTIFIER = " wall ";

    public boolean isApplicable(CommandParameter commandParameter) {
        return commandParameter.startsWith(WALL_IDENTIFIER);
    }

}
