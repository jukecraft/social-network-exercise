package timeline;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.builder.UserBuilder.aUser;

import java.util.List;

import org.junit.Test;

public class SocialNetworkTest {
    @Test
    public void givenNoRegisteredFollowersThenItReturnsNoFollowers() {
        SocialNetwork socialNetwork = new SocialNetwork();

        List<User> following = socialNetwork.getFollowing(aUser().create());

        assertThat(following, is(empty()));
    }
}
