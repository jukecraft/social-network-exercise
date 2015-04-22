package accepting;

import static java.text.MessageFormat.format;

public class Post implements Comparable<Post> {

    private static final String POST_PRINT_FORMAT = "{0} ({1})";
    private Message message;
    private SocialTime postingTime;

    public Post(Message message, SocialTime postingTime) {
        this.message = message;
        this.postingTime = postingTime;
    }

    public String printTimestamp(SocialTime printingTime) {
        return format(POST_PRINT_FORMAT, message, postingTime.printTimestamp(printingTime));
    }

    @Override
    public int compareTo(Post other) {
        return this.postingTime.compareTo(other.postingTime);
    }

}
