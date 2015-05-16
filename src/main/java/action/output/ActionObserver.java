package action.output;

import posts.output.Output;

public interface ActionObserver {

    void update(Output output);

}