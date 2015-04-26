package timeline;

import static commands.UserBuilder.aUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static timeline.OutputBuilder.anEmptyOutput;
import static timeline.PostBuilder.aPost;
import static timeline.SocialTimeBuilder.aTime;

import org.junit.Test;

import time.SocialTime;

import commands.User;

public class TimelineServiceTest {
    private static final User ALICE = aUser().withName("Alice").create();
    private static final User BOB = aUser().withName("Bob").create();
    private static final SocialTime PRINTING_TIME = aTime().create();
    private static final Post A_POST = aPost().withMessage("a post").create();
    private static final Post ANOTHER_POST = aPost().withMessage("another post").create();

    private TimelineService timelines = new TimelineService();

    @Test
    public void givenEmptyTimelinesNoPostsAreReturned() {
        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline, is(anEmptyOutput().create()));
    }

    @Test
    public void givenEmptyTimelinesWhenAlicePublishesAPostHerPostIsReturned() {
        timelines.post(ALICE, A_POST);

        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline, is(anEmptyOutput() //
            .withLine(A_POST.printAt(PRINTING_TIME)) //
            .create()));
    }

    @Test
    public void givenAliceAndBobPublishedPostsWhenAliceTimelineIsRequestedOnlyHerPostIsReturned() {
        timelines.post(ALICE, A_POST);
        timelines.post(BOB, ANOTHER_POST);

        Output alicesTimeline = timelines.printTimeline(ALICE, PRINTING_TIME);

        assertThat(alicesTimeline, is(anEmptyOutput() //
            .withLine(A_POST.printAt(PRINTING_TIME)) //
            .create()));
    }

}
