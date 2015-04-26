package timeline;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import time.SocialTime;

import commons.SocialNetworkingValueObject;

public class WallOutput extends SocialNetworkingValueObject implements Output {

    private Map<User, PostsOutput> timelines = new HashMap<>();

    public void addPosts(User user, PostsOutput posts) {
        timelines.put(user, posts);
    }

    @Override
    public List<String> print(SocialTime printingTime) {
        List<String> output = new ArrayList<>();
        timelines.forEach((user, posts) -> output.addAll(printPosts(user, posts, printingTime)));
        return output;
    }

    private List<String> printPosts(User user, PostsOutput posts, SocialTime printingTime) {
        return posts.print(printingTime) //
            .stream() //
            .map(post -> user + " - " + post) //
            .collect(toList());
    }

    @Override
    public List<String> printWithUser(SocialTime printingTime) {
        // TODO Auto-generated method stub
        return null;
    }

}
