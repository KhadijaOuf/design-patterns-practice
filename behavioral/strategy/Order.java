// Context: holds a strategy and delegates the shipping calculation to it
public class Order {

    private ShippingStrategy strategy;
    private double totalCost;
    private double weight;
    private String country;

    // construct an order with a specefic strategy
    public Order(ShippingStrategy strategy, int totalCost, int weight, String country) {
        if (strategy == null) {
            throw new IllegalArgumentException("Shipping strategy must not be null.");
        }
        if (totalCost < 0 || weight < 0) {
            throw new IllegalArgumentException("Total cost and weight must not be negative.");
        }
        this.strategy = strategy;
        this.totalCost = totalCost;
        this.weight = weight;
        this.country = country;
    }

    // get the order's total cost
    public double getTotalCost() {
        return this.totalCost;
    }

    // get the order's total weight
    public double getWeight() {
        return this.weight;
    }

    // get the order's country
    public String getCountry() {
        return this.country;
    }

    // get the current order strategy
    public ShippingStrategy getStrategy() {
        return this.strategy;
    }

    // set the order strategy to a new one
    public void setStrategy(ShippingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Shipping strategy must not be null.");
        }
        this.strategy = strategy;
    }

    public double getShippingCost() {
        return strategy.calculate(this);
    }

}
