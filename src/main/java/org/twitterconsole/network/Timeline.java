package org.twitterconsole.network;

import java.util.ArrayList;
import java.util.List;

import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.output.PostsOutput;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public PostsOutput collectPosts() {
        return new PostsOutput(posts);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

}
