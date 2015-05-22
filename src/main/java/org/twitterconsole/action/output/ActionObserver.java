package org.twitterconsole.action.output;

import org.twitterconsole.posts.output.Output;

public interface ActionObserver {

    void update(Output output);

}