package org.twitterconsole.network;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.twitterconsole.posts.PostBuilder.aPost;
import static org.twitterconsole.posts.UserBuilder.aUserNamedAlice;
import static org.twitterconsole.posts.UserBuilder.aUserNamedBob;
import static org.twitterconsole.posts.output.PostsOutputBuilder.anEmptyPostsOutput;

import org.junit.Test;
import org.twitterconsole.posts.Post;
import org.twitterconsole.posts.User;
import org.twitterconsole.posts.output.PostsOutput;

public class PostRepositoryShould {
    private static final User ALICE = aUserNamedAlice();
    private static final Post A_POST_FROM_ALICE = aPost().withAuthor(ALICE).create();
    private static final Post A_POST_FROM_BOB = aPost().withAuthor(aUserNamedBob()).create();

    private PostRepository postRepository = new PostRepository();

    @Test
    public void returnAnEmptyOutputIfThereAreNoPosts() {
        PostsOutput alicesTimeline = postRepository.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput().create()));
    }

    @Test
    public void returnAlicesPosts() {
        postRepository.post(A_POST_FROM_ALICE);

        PostsOutput alicesTimeline = postRepository.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput()
            .withPost(A_POST_FROM_ALICE)
            .create()));
    }

    @Test
    public void returnOnlyAlicesPostsIfThereArePostsFromAnotherUser
        () {
        postRepository.post(A_POST_FROM_ALICE);
        postRepository.post(A_POST_FROM_BOB);

        PostsOutput alicesTimeline = postRepository.collectPosts(ALICE);

        assertThat(alicesTimeline, is(anEmptyPostsOutput()
            .withPost(A_POST_FROM_ALICE)
            .create()));
    }

}
