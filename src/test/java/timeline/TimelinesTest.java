package timeline;

import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;

import commands.User;

public class TimelinesTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final SocialTime PRINTING_TIME = aTime().create();
    private static final Post A_POST = aPost().withMessage("a post").create();
    private static final Post ANOTHER_POST = aPost().withMessage("another post").create();

    private Timelines timelines = new Timelines();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline, is(new Output()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(ALICE, A_POST);

        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline.getOutput(), contains(A_POST.printAt(PRINTING_TIME)));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(ALICE, A_POST);
        timelines.post(aUser().withName("Bob").create(), ANOTHER_POST);

        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline.getOutput(), contains(A_POST.printAt(PRINTING_TIME)));
    }

}
