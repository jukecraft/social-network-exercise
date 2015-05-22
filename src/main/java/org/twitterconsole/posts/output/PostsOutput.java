package org.twitterconsole.posts.output;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.twitterconsole.commons.SocialNetworkingValueObject;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.SocialTime;

public class PostsOutput extends SocialNetworkingValueObject implements Output {

    private List<Post> posts;

    public PostsOutput(List<Post> posts) {
        this.posts = posts;
    }

    public PostsOutput() {
        this(new ArrayList<>());
    }

    @Override
    public List<String> print(SocialTime printingTime) {
        return printAllWith(post -> post.printAt(printingTime));
    }

    public List<String> printWithUser(SocialTime printingTime) {
        return printAllWith(post -> post.printWithUser(printingTime));
    }

    private List<String> printAllWith(Function<Post, String> printingOption) {
        return posts.stream()
            .sorted()
            .map(printingOption)
            .collect(toList());
    }

    public PostsOutput mergeWith(PostsOutput other) {
        List<Post> mergedPosts = new ArrayList<>(posts);
        mergedPosts.addAll(other.posts);
        return new PostsOutput(mergedPosts);
    }
}
