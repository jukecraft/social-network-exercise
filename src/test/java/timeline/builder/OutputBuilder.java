package timeline.builder;

import static timeline.builder.PostBuilder.aPost;

import java.util.ArrayList;
import java.util.List;

import timeline.Output;
import timeline.Post;

public class OutputBuilder {

    public static OutputBuilder anEmptyOutput() {
        return new OutputBuilder();
    }

    public static OutputBuilder anOutput() {
        return new OutputBuilder() //
            .withPost(aPost().create());
    }

    private List<Post> posts = new ArrayList<>();

    public OutputBuilder withPost(Post post) {
        posts.add(post);
        return this;
    }

    public Output create() {
        return new Output(posts);
    }

}
