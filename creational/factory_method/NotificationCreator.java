
public abstract class NotificationCreator {
    // each subclass will implement it's own implementation to create the appropriate notification type
    public abstract Notification createNotification();

    public void notifyUser(String message){
        this.createNotification().sendNotification(message);
    }

}

// ezemple of implementation to avoid :

    // public String notificationType;
    // public Notification createNotification() {
    //     if (notificationType == null || notificationType.isEmpty())
    //         return null;
    //     if ("SMS".equalsIgnoreCase(notificationType)) {
    //         return new SmsNotification();
    //     } else if ("EMAIL".equalsIgnoreCase(notificationType)) {
    //         return new EmailNotification();
    //     } else if ("PUSH".equalsIgnoreCase(notificationType)) {
    //         return new PushNotification();
    //     }
    //     return null;
        
