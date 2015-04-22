package accepting;

public class Post {

    private Message message;

    public Post(Message message, PostingTime postingTime) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message.toString();
    }

    public String printTimestamp(PostingTime postingTime) {
        return " (5 minutes ago)";
    }

}
