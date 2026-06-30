package org.example.payment;

import org.example.model.Order;
import org.example.model.PaymentResult;

public class PaymentProcessor {
    public PaymentResult process(Order order, PaymentMethod paymentMethod){
        // TODO: prevent paying already paid orders ( done )
        if(order.isPaid()) {
            return new PaymentResult(false,"This order was already paid for");
        }
        // TODO: prevent paying empty orders ( done )
        if(order.getItems().isEmpty()) {
            return new PaymentResult(false, "You need to add items in your order before paying.");
        }

        PaymentResult result = paymentMethod.pay(order.calculateTotal());

        if(result.isSuccessful()){
            order.markAsPaid();
        }

        return result;
    }
}
