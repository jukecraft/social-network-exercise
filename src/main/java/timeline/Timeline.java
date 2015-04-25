package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public List<String> printTimeline(SocialTime printingTime) {
        return posts.stream() //
            .sorted() //
            .map(post -> post.printAt(printingTime)) //
            .collect(toList());
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
