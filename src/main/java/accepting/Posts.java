package accepting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Posts {
    private List<Post> posts = new ArrayList<>();

    public List<String> printPosts(SocialTime socialTime) {
        if (posts.isEmpty())
            return Collections.emptyList();
        return Arrays.asList(posts.get(0).printTimestamp(socialTime));
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
