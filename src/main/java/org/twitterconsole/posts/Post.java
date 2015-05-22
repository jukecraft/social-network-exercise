package org.twitterconsole.posts;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class Post implements Comparable<Post> {
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

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }

}
