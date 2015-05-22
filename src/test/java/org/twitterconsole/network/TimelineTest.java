package org.twitterconsole.network;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.PostBuilder.aPost;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.output.PostsOutput;

public class TimelineTest {
    private static final Post A_POST = aPost() //
        .withPostingTime(now()) //
        .create();
    private static final Post LATER_POST = aPost() //
        .withPostingTime(now().plusYears(1)) //
        .create();

    private Timeline timeline = new Timeline();

    @Test
    public void givenAnEmptyListOfPostsWhenThePostsArePrintedItReturnsNoOutput() {
        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void givenAListOfOnePostWhenThePostsArePrintedItReturnsOutputWithThatPost() {
        timeline.addPost(A_POST);

        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .create()));
    }

    @Test
    public void givenAListOfPostsWhenThePostsArePrintedItReturnsOutputWithBothPosts() {
        timeline.addPost(A_POST);
        timeline.addPost(LATER_POST);

        PostsOutput output = timeline.collectPosts();

        assertThat(output, is(anEmptyPostsOutput() //
            .withPost(A_POST) //
            .withPost(LATER_POST) //
            .create()));
    }
}
