package accepting;

public class Post {

    private Message message;

    public Post(Message message, SocialTime postingTime) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message.toString();
    }

    public String printTimestamp(SocialTime postingTime) {
        return " (5 minutes ago)";
    }

}
