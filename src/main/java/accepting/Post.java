package accepting;

public class Post {

    private Message message;

    public Post(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message.toString();
    }

}
