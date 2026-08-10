package com.linkedin.importingmodules;

import module java.base;

public class DataProcessorImportClasses {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // Collections
        List<String> names = new ArrayList<>();
        Map<String, Integer> counts = new HashMap<>();
        Set<String> unique = new HashSet<>();

        // Date/time
        LocalDate today = LocalDate.now();
        String formatted = today.format(DateTimeFormatter.ISO_DATE);
        System.out.println("Today: " + formatted);

        // Streams
        List<String> upper = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // File I/O
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names.add(line);
            }
        }

        // NIO
        Files.writeString(Path.of("output.txt"), String.join(",", upper));

        // Concurrency
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.println(future.get());
    }
}
