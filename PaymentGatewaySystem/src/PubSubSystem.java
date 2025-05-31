import java.util.HashMap;

public class PubSubSystem {
    public static class Topic
    {
        String name;

        public Topic(String name) {
            this.name = name;
        }
    }
    public static class  Listners
    {
        String message;
        public void setMessage(String message)
        {

        }
        public String getMessage() {
            return message;
        }
    }
    public static class  Producers
    {
        String message;

        public Producers(String message) {
            this.message = message;
        }
    }
    public class PubSubQueue
    {
        HashMap<Topic,Listners> mpTopicListner;

    }
    public static void main(String args[])
    {

    }
}
