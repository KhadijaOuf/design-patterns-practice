
public class Main {

    public static void main(String[] args) {
        Order order1 = new Order(new StandardShipping(), 80, 5, "Europe");
        Order order2 = new Order(new LocalPickupShipping(), 50, 10, "USA");
        Order order3 = new Order(new ExpressShipping(), 200, 20, "Canada");
        Order order4 = new Order(new InternationalShipping(), 75, 15, "Australia");

        System.out.println("Order 1 (Standard): " + order1.getShippingCost());
        System.out.println("Order 2 (Local Pickup): " + order2.getShippingCost());
        System.out.println("Order 3 (Express): " + order3.getShippingCost());
        System.out.println("Order 4 (International): " + order4.getShippingCost());

        // Swap the strategy at runtime, without touching Order
        order1.setStrategy(new ExpressShipping());
        System.out.println("Order 1 after switching to Express: " + order1.getShippingCost());

        // Invalid case: too heavy for international shipping
        try {
            Order heavy = new Order(new InternationalShipping(), 300, 35, "Japan");
            System.out.println(heavy.getShippingCost());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}