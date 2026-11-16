import java.util.List;

public class Main {

    public static void main(String[] args) {
        // The client only knows the abstract creator type
        NotificationCreator creator = new EmailCreator();
        creator.notifyUser("Your order has shipped");

        // Switching channel = changing one line
        creator = new SmsCreator();
        creator.notifyUser("Your verification code is 4821");

        // All creators are used the same way, through the abstract type
        List<NotificationCreator> creators = List.of(
                new EmailCreator(),
                new SmsCreator(),
                new PushCreator());

        for (NotificationCreator c : creators) {
            c.notifyUser("Weekly summary available");
        }
    }
}