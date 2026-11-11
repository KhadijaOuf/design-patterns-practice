
public class StandardShipping implements ShippingStrategy {

    private static final double FREE_SHIPPING_THRESHOLD = 100.00;
    private static final double BASE_FEE = 5.00;
    private static final double FEE_PER_KG = 1.50;

    @Override
    public double calculate(Order order) {
        // free shipping for orders over 100
        if (order.getTotalCost() >= FREE_SHIPPING_THRESHOLD) {
            return 0.00;
        }
        // 5.00 + 1.50 × weight
        return BASE_FEE + FEE_PER_KG * order.getWeight();
    }
}