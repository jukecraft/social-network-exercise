package network;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static posts.UserBuilder.aUserNamedAlice;
import static posts.UserBuilder.aUserNamedBob;

import java.util.List;

import org.junit.Test;

import posts.User;

public class SocialNetworkTest {
    private static final User ALICE = aUserNamedAlice();
    private static final User BOB = aUserNamedBob();

    @Test
    public void givenFollowingWhenAskedForTheFollowingThenItReturnsNoFollowers() {
        SocialNetwork socialNetwork = new SocialNetwork();

        List<User> following = socialNetwork.getFollowing(ALICE);

        assertThat(following, is(empty()));
    }

    @Test
    public void givenOneRegisteredFollowingWhenAskedForTheFollowingThenItReturnsThatFollowers() {
        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.registerFollowing(ALICE, BOB);

        List<User> following = socialNetwork.getFollowing(ALICE);

        assertThat(following, contains(BOB));
    }
}
