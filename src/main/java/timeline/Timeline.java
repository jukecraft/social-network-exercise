package timeline;

import java.util.ArrayList;
import java.util.List;

import time.SocialTime;

public class Timeline {
    private List<Post> posts = new ArrayList<>();

    public Output printTimeline(SocialTime printingTime) {
        return new Output(posts);
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
