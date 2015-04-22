package accepting;

import static java.time.Duration.between;

import java.time.Duration;
import java.time.LocalDateTime;

public class PostingTime {
    public LocalDateTime postingTime;

    public PostingTime(LocalDateTime postingTime) {
        this.postingTime = postingTime;
    }

    public PostingTime plusMinutes(int numberOfMinutes) {
        return new PostingTime(postingTime.plusMinutes(numberOfMinutes));
    }

    public String printTimestamp(PostingTime postingTime) {
        Duration timePassed = between(this.postingTime, postingTime.postingTime);
        long numberOfMinutes = timePassed.toMinutes();
        return numberOfMinutes + " minutes ago";
    }
}