package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public Output printTimeline(SocialTime printingTime) {
        return new Output(posts.stream() //
            .sorted() //
            .map(post -> post.printAt(printingTime)) //
            .collect(toList()));
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
