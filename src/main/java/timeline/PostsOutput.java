package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import time.SocialTime;

import commons.SocialNetworkingValueObject;

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

    @Override
    public List<String> printWithUser(SocialTime printingTime) {
        return printAllWith(post -> post.printWithUser(printingTime));
    }

    private List<String> printAllWith(Function<Post, String> printingOption) {
        return posts.stream() //
            .sorted() //
            .map(printingOption) //
            .collect(toList());
    }

    public Output mergeWith(PostsOutput anotherOutput) {
        // TODO Auto-generated method stub
        return null;
    }
}
