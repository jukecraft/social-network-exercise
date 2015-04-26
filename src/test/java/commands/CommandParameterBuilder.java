package commands;

public class CommandParameterBuilder {

    private String user = "Alice";
    private String command = "";

    public static CommandParameterBuilder aCommand() {
        return new CommandParameterBuilder();
    }

    public static CommandParameterBuilder aPostCommand() {
        return new CommandParameterBuilder() //
            .withMessage("I love the weather today!");
    }

    public CommandParameterBuilder withMessage(String messageText) {
        return withCommand(" -> " + messageText);
    }

    public CommandParameterBuilder withCommand(String command) {
        this.command = command;
        return this;
    }

    public CommandParameterBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public CommandParameter create() {
        return new CommandParameter(user + command);
    }

}
