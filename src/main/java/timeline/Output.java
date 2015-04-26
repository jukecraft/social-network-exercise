package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

import commons.SocialNetworkingValueObject;

public class Output extends SocialNetworkingValueObject {

    private List<Post> posts;

    public Output(List<Post> posts) {
        this.posts = posts;
    }

    public Output() {
        posts = new ArrayList<>();
    }

    public List<String> getOutput(SocialTime printingTime) {
        return posts.stream() //
            .sorted() //
            .map(post -> post.printAt(printingTime)) //
            .collect(toList());
    }

}
