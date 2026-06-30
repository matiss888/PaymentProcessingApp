package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String customerName;
    private final List<OrderItem> items;
    private OrderStatus status;
    private Discount discount = new NoDiscount();

    public Order(Builder builder) {
        this.customerName = builder.customerName;
        this.items = builder.items;
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item){
        // TODO: prevent adding items if order is already paid ( done )
        if(this.status == OrderStatus.PAID) {
            System.out.println("This order has already been paid for.");
            return;
        }
        items.add(item);
    }

    public double calculateTotal(){
        // TODO: calculate total from all order items (including discounts) ( done )
        double sum = 0;
        for(OrderItem item : items) {
            sum += item.calculateTotal();
        }
        return discount.apply(sum);
    }

    public void markAsPaid(){
        // TODO: validate order is not empty ( done )
        if(items.isEmpty()) {
            System.out.println("Make sure there is atleast one item in the order.");
            return;
        }
        this.status = OrderStatus.PAID;
    }

    public void applyDiscount(Discount discount){
        this.discount = discount;
    }

    public boolean isPaid(){
        return this.status == OrderStatus.PAID;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public String getCustomerName() {
        return customerName;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private String customerName;
        private List<OrderItem> items = new ArrayList<>();
        public Builder customerName(String customerName){
            this.customerName = customerName;
            return this;
        }
        public Builder addItem(OrderItem item){
            this.items.add(item);
            return this;
        }
        public Order build(){
            // TODO: validate customerName ( done )
            if(customerName.isBlank()) {
                System.out.println("Customer name can't be empty. Please enter your name.");
                return null;
            }
            return new Order(this);
        }
    }
}
