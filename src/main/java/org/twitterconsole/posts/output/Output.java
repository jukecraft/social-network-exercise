package org.twitterconsole.posts.output;

import java.util.List;

import org.twitterconsole.posts.SocialTime;

public interface Output {
    List<String> print(SocialTime printingTime);

}