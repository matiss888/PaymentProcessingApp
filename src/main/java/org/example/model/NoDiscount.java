package org.example.model;

public class NoDiscount extends Discount {
    public NoDiscount() {
        super("NONE");
    }

    @Override
    public double apply(double originalAmount) {
        // TODO ( changed 0 to original amount since no discount ) ( done )
        return originalAmount;
    }
}
