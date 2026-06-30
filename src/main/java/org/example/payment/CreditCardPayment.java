package org.example.payment;

import org.example.model.PaymentResult;

public class CreditCardPayment extends PaymentMethod {
    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        super("CreditCard");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        // TODO: add basic validations ( done )
        if(cardHolderName.isBlank() || cardNumber.isBlank()) {
            return new PaymentResult(false, "Card Holder Name or Card number can't be empty");
        }
        if(cardNumber.length() != 16) {
            return new PaymentResult(false, "Credit cards consist of 16 . Write without spaces.");
        }
        return new PaymentResult(true, "Paid " + amount + " using credit card ending with " + cardNumber.substring(cardNumber.length() - 4));
    }
}
