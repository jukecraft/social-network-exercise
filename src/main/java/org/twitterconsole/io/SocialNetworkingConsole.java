package org.twitterconsole.io;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;
import java.util.Scanner;

public class SocialNetworkingConsole {

    private static final String PROMPT = "> ";

    public void print(Iterable<? extends String> output) {
        output.forEach(line -> System.out.println(line));
    }

    public Optional<Command> getNextCommand() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return of(new Command(scanner.nextLine()));
        return empty();
    }

    public void printPrompt() {
        System.out.print(PROMPT);
    }

}
