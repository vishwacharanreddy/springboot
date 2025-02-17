package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	//s is a placeholder of run time value
	private static final String template = "Welcome, %s!";
	// AtomicLong is a datatype that will handle huge data
	
	private final AtomicLong atomicLong = new AtomicLong();
	
	@GetMapping("/sample")
	public Sample sample(@RequestParam(value = "msg", defaultValue = "Everyone") String msg)
	{ 		
		return new Sample(atomicLong.incrementAndGet(), String.format(template, msg)); 	
	}
	 

}