package accepting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Posts {
    private List<Post> posts = new ArrayList<>();

    public List<String> printPosts(SocialTime printingTime) {
        return posts.stream().map(post -> post.printTimestamp(printingTime)).collect(Collectors.toList());
    }

    public void addPost(Post create) {
        posts.add(create);

    }

}
