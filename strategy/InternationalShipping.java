
public class InternationalShipping implements ShippingStrategy {

    private static final double MAX_WEIGHT_KG = 30.0;
    private static final double EUROPE_BASE_FEE = 25.00;
    private static final double WORLD_BASE_FEE = 40.00;
    private static final double FEE_PER_KG = 6.00;
    private static final double CUSTOMS_RATE = 0.05;

    @Override
    public double calculate(Order order) {
        if (order.getWeight() > MAX_WEIGHT_KG) {
            throw new IllegalArgumentException(
                    "Weight exceeds the limit for international shipping (" + MAX_WEIGHT_KG + " kg).");
        }
        double base = order.getCountry().equalsIgnoreCase("Europe") ? EUROPE_BASE_FEE : WORLD_BASE_FEE;
        return base + (FEE_PER_KG * order.getWeight()) + (CUSTOMS_RATE * order.getTotalCost());
    }
}
