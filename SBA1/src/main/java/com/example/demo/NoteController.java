package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    // Placeholder for runtime value
    private static final String template = "Note: %s!";
    // AtomicLong to handle unique ID generation
    private final AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/note")
    public Note note(@RequestParam(value = "content", defaultValue = "Default Note") String content) {
        return new Note(atomicLong.incrementAndGet(), String.format(template, content));
    }
}