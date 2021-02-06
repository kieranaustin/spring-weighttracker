package com.weighttracker.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController
{
	private static final String template = "Helloooo, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/{name}")
	//public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
	public Greeting greeting(@PathVariable String name)
	{
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
