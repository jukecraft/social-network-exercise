package accepting;

public class Command {

    private static final String COMMAND_SEPERATOR = " ";
    private String user;
    private Message message;

    public Command(String command) {
        String[] commandParts = command.split(COMMAND_SEPERATOR);
        user = commandParts[0];
        message = new Message(command.substring(user.length()));
    }

    public String getUser() {
        return user;
    }

    public Message getMessage() {
        return message;
    }

}
