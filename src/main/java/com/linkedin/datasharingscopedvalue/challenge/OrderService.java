package com.linkedin.datasharingscopedvalue.challenge;

import static java.lang.ScopedValue.where;


public class OrderService {

    // OLD: Using ThreadLocal
    private static final ScopedValue<String> CURRENT_USER = ScopedValue.newInstance();

    public static void main(String[] args) {
        // Simulate processing two orders
        where(CURRENT_USER, "Ouidad").run(() -> processOrder("Ouidad", "ORD-001", 99.99));
        where(CURRENT_USER, "Stephan").run(() -> processOrder("Stephan", "ORD-002", 149.99));
    }

    static void processOrder(String username, String orderId, double amount) {
        // Set the user context
            validateOrder(orderId, amount);
            saveOrder(orderId, amount);
            sendConfirmation(orderId);
        
    }

    static void validateOrder(String orderId, double amount) {
        log("Validating order " + orderId);
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }

    static void saveOrder(String orderId, double amount) {
        log("Saving order " + orderId + " for $" + amount);
    }

    static void sendConfirmation(String orderId) {
        log("Sending confirmation for order " + orderId);
    }

    static void log(String message) {
        // Read from ThreadLocal
        String user = CURRENT_USER.get();
        System.out.println("[User: " + user + "] " + message);
    }
}
