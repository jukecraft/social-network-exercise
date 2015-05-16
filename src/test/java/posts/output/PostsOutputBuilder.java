package posts.output;

import static posts.PostBuilder.onePost;

import java.util.ArrayList;
import java.util.List;

import posts.Post;

public class PostsOutputBuilder {

    public static PostsOutputBuilder anEmptyPostsOutput() {
        return new PostsOutputBuilder();
    }

    public static PostsOutputBuilder aPostsOutput() {
        return new PostsOutputBuilder() //
            .withPost(onePost());
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
