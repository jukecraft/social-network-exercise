package application;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;
import java.util.Scanner;

public class SocialNetworkingConsole {
    public void print(String output) {
        System.out.print(output);
    }

    public void print(Iterable<? extends String> output) {
        output.forEach(line -> System.out.println(line));
    }

    public Optional<String> getNextCommand() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return of(scanner.nextLine());
        return empty();
    }

}
