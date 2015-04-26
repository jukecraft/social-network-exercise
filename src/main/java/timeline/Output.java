package timeline;

import java.util.List;

import time.SocialTime;

public interface Output {
    List<String> print(SocialTime printingTime);

    List<String> printWithUser(SocialTime printingTime);

}