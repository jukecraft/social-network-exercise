package accepting;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

public class Posts {
    private List<Post> posts = new ArrayList<>();

    public List<String> printPosts(SocialTime printingTime) {
        if (posts.isEmpty())
            return emptyList();
        return asList(posts.get(0).printTimestamp(printingTime));
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
