package org.twitterconsole.action.available;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

import org.twitterconsole.action.output.ActionWithOutput;
import org.twitterconsole.io.Command;
import org.twitterconsole.network.TimelineService;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.Output;

public class WallAction implements ActionWithOutput {

    private static final String WALL_IDENTIFIER = " wall";
    private TimelineService timelineService;

    public WallAction(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @Override
    public void execute(Command command) {
        if (isExecutable(command))
            executeWithOutput(command);
    }

    @Override
    public Optional<Output> executeWithOutput(Command command) {
        if (isExecutable(command))
            return of(timelineService.collectWall(new User(command)));
        return empty();
    }

    private boolean isExecutable(Command command) {
        return command.startsWith(WALL_IDENTIFIER);
    }

}
