package accepting;

public class Post {

    private Message message;
    private SocialTime postingTime;

    public Post(Message message, SocialTime postingTime) {
        this.message = message;
        this.postingTime = postingTime;
    }

    public String printTimestamp(SocialTime postingTime) {
        return message.toString() + " (" + this.postingTime.printTimestamp(postingTime) + ")";
    }

}
