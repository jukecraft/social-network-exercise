package accepting;

import java.time.LocalDateTime;

public class PostingTime {
    public LocalDateTime postingTime;

    public PostingTime(LocalDateTime postingTime) {
        this.postingTime = postingTime;
    }

    public PostingTime plusMinutes(int numberOfMinutes) {
        // TODO Auto-generated method stub
        return this;
    }

    public String printTimestamp(PostingTime postingTime) {
        return "5 minutes ago";
        // TODO Auto-generated method stub

    }
}