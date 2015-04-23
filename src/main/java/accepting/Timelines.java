package accepting;

public class Timelines {

    private Timeline timeline = new Timeline();

    public Timeline getPostsFor(String user) {
        return timeline;
    }

    public void post(String user, Post post) {
        timeline.addPost(post);
    }

}
