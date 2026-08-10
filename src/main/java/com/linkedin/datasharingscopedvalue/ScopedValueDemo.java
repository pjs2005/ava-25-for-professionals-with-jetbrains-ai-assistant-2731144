package com.linkedin.datasharingscopedvalue;

import static java.lang.ScopedValue.where;
import java.lang.ScopedValue;

public class ScopedValueDemo {

    // Step 1: define a ScopedValue
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    void main(String[] args) {
        // Step 2: bind a value for a scope
        where(REQUEST_ID, "REQ-42").run(
            () -> handleRequest()
        );
        where(REQUEST_ID, "REQ-43").run(
            () -> handleRequest()
        );
        where(REQUEST_ID, "REQ-44").run(
            () -> handleRequest());
    }

    static void handleRequest() {
        log("start");
        doWork();
        log("done");
    }

    static void doWork() {
        log("doing work...");
    }

    static void log(String msg) {
        // Step 3: read the value inside the scope
        System.out.println("[" + REQUEST_ID.orElse("no-id") + "] " + msg);
    }
}
