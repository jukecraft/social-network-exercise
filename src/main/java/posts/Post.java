package posts;

import static java.text.MessageFormat.format;

import commons.SocialNetworkingValueObject;

public class Post extends SocialNetworkingValueObject implements Comparable<Post> {
    private static final String POST_PRINT_FORMAT = "{0} ({1})";
    private static final String POST_WITH_USER_PRINT_FORMAT = "{0} - {1}";

    private final Message message;
    private final SocialTime postingTime;
    private User user;

    public Post(User user, Message message, SocialTime postingTime) {
        this.message = message;
        this.postingTime = postingTime;
        this.user = user;
    }

    public String printAt(SocialTime printingTime) {
        return format(POST_PRINT_FORMAT, message, postingTime.printTimestamp(printingTime));
    }

    public String printWithUser(SocialTime printingTime) {
        return format(POST_WITH_USER_PRINT_FORMAT, user, printAt(printingTime));
    }

    @Override
    public int compareTo(Post other) {
        return postingTime.compareTo(other.postingTime);
    }

}
