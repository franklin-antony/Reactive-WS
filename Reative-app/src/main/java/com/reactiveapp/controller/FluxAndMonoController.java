package com.reactiveapp.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class FluxAndMonoController {

		
	@GetMapping(value ="/steamFlux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Integer> steamFlux()
	{
		return Flux.just(1,3,5,7,9).delayElements(Duration.ofSeconds(1));
	}
}
