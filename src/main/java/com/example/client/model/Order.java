package com.example.client.model;

public class Order {

    private Long id;
    private String customerName;
    private String orderStatus;
    private String orderSummary;
    // Add other order fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                // Include other fields in the string representation
                '}';
    }
}
