package timeline;

import static java.util.Arrays.asList;
import static timeline.PostBuilder.aPost;

public class OutputBuilder {
    private Output output = new Output();

    public static OutputBuilder anEmptyOutput() {
        return new OutputBuilder();
    }

    public static OutputBuilder anOutput() {
        return new OutputBuilder() //
            .withPost(aPost().create());
    }

    public OutputBuilder withPost(Post post) {
        output.add(new Output(asList(post)));
        return this;
    }

    public Output create() {
        return output;
    }

}
