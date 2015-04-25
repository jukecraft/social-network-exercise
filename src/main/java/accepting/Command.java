package accepting;

public class Command {

    private String user;

    public Command(String command) {
        String[] commandParts = command.split(" ");
        user = commandParts[0];
    }

    public String getUser() {
        return user;
    }

}
