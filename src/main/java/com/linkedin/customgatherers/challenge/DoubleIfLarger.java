package com.linkedin.customgatherers.challenge;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class DoubleIfLarger {

    public static Gatherer<Integer, ?, Integer> doubleIfLarger() {
        class State {
            // TODO: Add fields to track if we've seen a previous element
            //       and what that previous value was
        }

        AtomicInteger previous = new AtomicInteger(Integer.MAX_VALUE);

        return Gatherer.ofSequential(
                State::new,
                Gatherer.Integrator.ofGreedy((state, elem, downstream) -> {
                    // TODO: Implement the logic:
                    // 1. If this is the first element, just emit it as-is
                    // 2. If elem > previous, emit elem * 2
                    // 3. Otherwise, emit elem as-is
                    // 4. Update state to remember this element

                    if(elem > previous.get()) {
                        downstream.push(elem * 2);
                    } else {
                        downstream.push(elem);
                    }

                    previous.set(elem);





                    return true; // continue processing
                })
        );
    }

    public static void main(String[] args) {
        Stream.of(5, 10, 3, 8, 12)
                .gather(doubleIfLarger())
                .forEach(System.out::println);
    }
}