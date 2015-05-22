package org.twitterconsole.posts;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.PostBuilder.aPost;
import static org.twitterconsole.posts.SocialTimeBuilder.aTime;
import static org.twitterconsole.posts.UserBuilder.aUser;

import java.util.List;

import org.junit.Test;

public class PostTest {
    private static final String MESSAGE_TEXT = "I love the weather today";
    private static final String ANOTHER_MESSAGE_TEXT = "Damn! We lost!";

    @Test
    public void createdWithFilledMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost()
            .withMessage(MESSAGE_TEXT)
            .withPostingTime(aTime())
            .create();

        String printedTimestamp = post.printAt(aTime()
            .plusMinutes(5)
            .create());

        assertThat(printedTimestamp, is(MESSAGE_TEXT + " (5 minutes ago)"));
    }

    @Test
    public void createdWithOtherMessageAndPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost()
            .withMessage(ANOTHER_MESSAGE_TEXT)
            .withPostingTime(aTime())
            .create();

        String printedTimestamp = post.printAt(aTime()
            .plusMinutes(5)
            .create());

        assertThat(printedTimestamp, is(ANOTHER_MESSAGE_TEXT + " (5 minutes ago)"));
    }

    @Test
    public void createdWithMessageAndOtherPostingTimeItPrintsThatMessageAndTheTimePassedSinceThen() {
        Post post = aPost()
            .withMessage(MESSAGE_TEXT)
            .withPostingTime(aTime())
            .create();

        String printedTimestamp = post.printAt(aTime()
            .plusMinutes(10)
            .create());

        assertThat(printedTimestamp, is(MESSAGE_TEXT + " (10 minutes ago)"));
    }

    @Test
    public void createdWithMessageAndOtherPostingTimeAndUserItPrintsTheUserTheMessageAndTheTimePassedSinceThen() {
        User user = aUser().withName("name").create();
        Post post = aPost()
            .withMessage(MESSAGE_TEXT)
            .withPostingTime(aTime())
            .withUser(user)
            .create();

        String printedTimestamp = post.printWithUser(aTime()
            .plusMinutes(10)
            .create());

        assertThat(printedTimestamp, is(user + " - " + MESSAGE_TEXT + " (10 minutes ago)"));
    }

    @Test
    public void twoPostsAreSortedByPostingTimeLatestFirst() {
        Post post = aPost()
            .withPostingTime(aTime())
            .create();
        Post laterPost = aPost()
            .withPostingTime(aTime().plusMinutes(1))
            .create();

        List<Post> posts = asList(post, laterPost);

        sort(posts);

        assertThat(posts, contains(laterPost, post));
    }

}
