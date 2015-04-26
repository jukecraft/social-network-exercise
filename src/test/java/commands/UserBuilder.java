package commands;

public class UserBuilder {
    private String name = "userName";

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public User create() {
        return new User(new CommandParameter(name));
    }
}
