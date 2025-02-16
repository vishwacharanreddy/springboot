package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller manages car-related requests.
 * It provides:
 * 1. Car information retrieval
 * 2. A request counter for tracking API usage
 */
@RestController
public class CarController {

    private static final String defaultTemplate = "Car: %s, Brand: %s";
    private static final String electricTemplate = "Electric Car: %s, Brand: %s";
    private static final String sportsTemplate = "Sports Car: %s, Brand: %s";
    
    private final AtomicLong counter = new AtomicLong(); // Counter for tracking requests

    // Main endpoint to get car details
    @GetMapping("/car")
    public Car getCar(@RequestParam(value = "model", defaultValue = "Corolla") String model,
                      @RequestParam(value = "brand", defaultValue = "Toyota") String brand,
                      @RequestParam(value = "type", defaultValue = "regular") String carType) {

        String description = String.format(defaultTemplate, model, brand); // Default car description

        // Modify description based on car type
        if ("electric".equalsIgnoreCase(carType)) {
            description = String.format(electricTemplate, model, brand);
        } else if ("sports".equalsIgnoreCase(carType)) {
            description = String.format(sportsTemplate, model, brand);
        }

        return new Car(counter.incrementAndGet(), model, brand);
    }

    // Endpoint to get request count
    @GetMapping("/carCount")
    public String getCarRequestCount() {
        return "The car endpoint has been requested " + counter.get() + " times.";
    }
}