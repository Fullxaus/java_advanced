package ru.mentee.power.lombok.example;

public class RegionBasedShipping implements ShippingStrategy {
    @Override
    public double calculate(Order_Lombok orderLombok) {
        String region = orderLombok.getDestinationRegion();
        if (region == null) {
            return 12.0;
        }
        switch (region) {
            case "Центр":
                return 5.0;
            case "Север":
                return 15.0;
            default:
                return 12.0;
        }
    }
}
