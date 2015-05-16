package command.output;

import posts.output.Output;

public interface CommandObserver {

    void update(Output output);

}