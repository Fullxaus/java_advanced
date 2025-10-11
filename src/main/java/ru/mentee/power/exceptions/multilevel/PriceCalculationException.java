package ru.mentee.power.exceptions.multilevel;

public class PriceCalculationException extends OrderException {
    private final ProductItem product;

    public PriceCalculationException(String message, ProductItem product) {
        super(message);
        this.product = product;
    }

    public PriceCalculationException(String message, ProductItem product, Throwable cause) {
        super(message, cause);
        this.product = product;
    }

    public ProductItem getProduct() { return product; }
}

