package accepting;

public class Post {

    private Message message;

    public Post(Message message, SocialTime postingTime) {
        this.message = message;
    }

    public String printTimestamp(SocialTime postingTime) {
        return message.toString() + " (5 minutes ago)";
    }

}
