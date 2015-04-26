package timeline;

import java.util.List;

import time.SocialTime;

public interface Output {

    List<String> getOutput(SocialTime printingTime);

}