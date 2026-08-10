package com.linkedin.importingmodules.challenge;

import module java.base;

public class ShoppingCart {

    private List<Item> items;
    private LocalDateTime createdAt;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public void addItem(String name, BigDecimal price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Optional<Item> findMostExpensive() {
        return items.stream()
                .max((a, b) -> a.price().compareTo(b.price()));
    }

    public Map<String, Integer> getItemCounts() {
        return items.stream()
                .collect(Collectors.toMap(
                        Item::name,
                        Item::quantity,
                        Integer::sum
                ));
    }

    public void saveReceipt(String filename) throws IOException {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Shopping Cart Receipt\n");
        receipt.append("Created: ")
                .append(createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .append("\n\n");

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        for (Item item : items) {
            receipt.append(String.format("%s x%d - %s\n",
                    item.name(),
                    item.quantity(),
                    currencyFormat.format(item.price())
            ));
        }

        receipt.append("\nTotal: ")
                .append(currencyFormat.format(calculateTotal()));

        Files.writeString(Path.of(filename), receipt.toString());
    }

    void main(String[] args) throws IOException {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Laptop", new BigDecimal("999.99"), 1);
        cart.addItem("Mouse", new BigDecimal("25.50"), 2);
        cart.addItem("Keyboard", new BigDecimal("79.99"), 1);

        System.out.println("Total: $" + cart.calculateTotal());

        cart.findMostExpensive().ifPresent(item ->
                System.out.println("Most expensive: " + item.name())
        );

        System.out.println("Item counts: " + cart.getItemCounts());

        cart.saveReceipt("receipt.txt");
        System.out.println("Receipt saved!");
    }
}

record Item(String name, BigDecimal price, int quantity) {}
