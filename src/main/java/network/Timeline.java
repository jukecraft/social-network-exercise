package network;

import java.util.ArrayList;
import java.util.List;

import posts.Post;
import posts.output.PostsOutput;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public PostsOutput collectPosts() {
        return new PostsOutput(posts);
    }

    public void addPost(Post create) {
        posts.add(create);
    }

}
