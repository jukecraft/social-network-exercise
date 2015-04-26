package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

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
        return posts.stream() //
            .sorted() //
            .map(post -> post.printAt(printingTime)) //
            .collect(toList());
    }

}
