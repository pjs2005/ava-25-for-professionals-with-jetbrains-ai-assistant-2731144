package com.linkedin.unnamedvariables.challenge;

record Book(String title, String author, int pages, double price) {}

record Student(String name, int age, String major, double gpa) {}

public class Library {

    // Method 1: Get book summary
    static String getBookSummary(Object obj) {
        return switch (obj) {
            case Book(String title, String author,  _,  _) ->
                    "Book: " + title + " by " + author;
            default -> "Not a book";
        };
    }

    // Method 2: Load book from file
    static Book loadBook(String filename) {
        try {
            // Simulate file operations
            // ...
            return new Book("Sample", "Author", 300, 19.99);
        } catch (Exception _) {
            // We don't actually use the exception details
            System.out.println("Failed to load book, using default");
            return new Book("Default", "Unknown", 0, 0.0);
        }
    }
}
