package io;

import java.util.Scanner;

public class SocialNetworkingConsole {
    public void print(String output) {
        System.out.print(output);
    }

    public void print(Iterable<? extends String> output) {
        output.forEach(line -> System.out.println(line));
    }

    public CommandParameter getNextCommand() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return new CommandParameter(scanner.nextLine());
        return CommandParameter.NOTHING;
    }

}
