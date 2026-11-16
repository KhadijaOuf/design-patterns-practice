
public class ExpressShipping implements ShippingStrategy {

    private static final double BASE_FEE = 15.00;
    private static final double FEE_PER_KG = 3.00;

    @Override
    public double calculate(Order order) {
        // // 15.00 + 3.00 × weight
        return BASE_FEE + FEE_PER_KG * order.getWeight();
    }
}
