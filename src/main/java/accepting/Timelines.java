package accepting;

public class Timelines {

    private Timeline timeline = new Timeline();

    public Timeline getPostsFor(String username) {
        return timeline;
    }

    public void post(String string, Post post) {
        timeline.addPost(post);
        // TODO Auto-generated method stub

    }

}
