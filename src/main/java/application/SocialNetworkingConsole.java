package application;

import java.util.List;
import java.util.Optional;

public class SocialNetworkingConsole {

    public void print(String output) {
        System.out.print(output);
    }

    public void print(List<String> output) {
        output.forEach(line -> System.out.println(line));
    }

    public Optional<String> getNextCommand() {
        // TODO Auto-generated method stub
        return null;
    }
}
