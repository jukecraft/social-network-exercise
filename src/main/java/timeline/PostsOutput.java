package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

import commons.SocialNetworkingValueObject;

public class PostsOutput extends SocialNetworkingValueObject {

    private List<Post> posts;

    public PostsOutput(List<Post> posts) {
        this.posts = posts;
    }

    public PostsOutput() {
        posts = new ArrayList<>();
    }

    public List<String> getOutput(SocialTime printingTime) {
        return posts.stream() //
            .sorted() //
            .map(post -> post.printAt(printingTime)) //
            .collect(toList());
    }

}
