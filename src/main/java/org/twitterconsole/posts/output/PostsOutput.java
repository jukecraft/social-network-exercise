package org.twitterconsole.posts.output;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.SocialTime;

public class PostsOutput implements Output {

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

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
