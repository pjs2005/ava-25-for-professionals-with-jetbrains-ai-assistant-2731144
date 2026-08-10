package com.linkedin.customgatherers.challenge;

import java.util.List;
import java.util.stream.Gatherers;

public class BatchingWithGatherer {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // TODO: Replace the loop with a stream pipeline using Gatherers.windowFixed(3)
        numbers.stream()
                // Your code here
                .gather(Gatherers.windowFixed(3))
                .forEach(System.out::println);
    }
}