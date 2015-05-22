package org.twitterconsole.io;



public class CommandBuilder {

    private String user = "Alice";
    private String command = "";

    public static CommandBuilder aCommand() {
        return new CommandBuilder();
    }

    public static CommandBuilder aPostCommand() {
        return aCommand().withMessage("I love the weather today!");
    }

    public static CommandBuilder aFollowsCommand() {
        return aCommand().withCommand(" follows Bob");
    }

    public CommandBuilder withMessage(String messageText) {
        return withCommand(" -> " + messageText);
    }

    public CommandBuilder withCommand(String command) {
        this.command = command;
        return this;
    }

    public CommandBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public Command create() {
        return new Command(user + command);
    }

}
