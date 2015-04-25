package timeline;

import static java.text.MessageFormat.format;
import time.SocialTime;

import commons.SocialNetworkingValueObject;

public class Post extends SocialNetworkingValueObject implements Comparable<Post> {
    private static final String POST_PRINT_FORMAT = "{0} ({1})";

    private final Message message;
    private final SocialTime postingTime;

    public Post(Message message, SocialTime postingTime) {
        this.message = message;
        this.postingTime = postingTime;
    }

    public String printAt(SocialTime printingTime) {
        return format(POST_PRINT_FORMAT, message, postingTime.printTimestamp(printingTime));
    }

    @Override
    public int compareTo(Post other) {
        return this.postingTime.compareTo(other.postingTime);
    }

}
