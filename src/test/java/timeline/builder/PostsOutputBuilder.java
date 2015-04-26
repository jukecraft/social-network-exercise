package timeline.builder;

import static timeline.builder.PostBuilder.aPost;

import java.util.ArrayList;
import java.util.List;

import timeline.PostsOutput;
import timeline.Post;

public class PostsOutputBuilder {

    public static PostsOutputBuilder anEmptyPostsOutput() {
        return new PostsOutputBuilder();
    }

    public static PostsOutputBuilder aPostsOutput() {
        return new PostsOutputBuilder() //
            .withPost(aPost().create());
    }

    private List<Post> posts = new ArrayList<>();

    public PostsOutputBuilder withPost(Post post) {
        posts.add(post);
        return this;
    }

    public PostsOutput create() {
        return new PostsOutput(posts);
    }

}
