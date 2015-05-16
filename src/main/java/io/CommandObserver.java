package io;

import timeline.Output;

public interface CommandObserver {

    void update(Output output);

}