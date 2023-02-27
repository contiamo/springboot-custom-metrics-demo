package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/random")
	public ResponseEntity<String> random() {

		Timer.Sample sample = Timer.start();

		// pick a random status code from 200, 201, 401, 403, and 500
		int[] statusCodes = { 200, 201, 401, 403, 500 };
		int randomStatusCode = statusCodes[(int) (Math.random() * statusCodes.length)];

		// generate a random float between .5 and 2.5s
		// sleep for that many seconds
		double random = (Math.random() * 2 + .5);
		try {
			Thread.sleep((long) (random * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sample.stop(Metrics.timer(
				"sleep.latency",
				"tag1", "value1", "status", String.valueOf(randomStatusCode)));

		// then print "hello, sorry that you had to wait X seconds"
		// where X is the number of seconds you slept
		String msg = "hello, sorry that you had to wait " + random + " seconds";

		return ResponseEntity.status(randomStatusCode).body(msg);
	}

}