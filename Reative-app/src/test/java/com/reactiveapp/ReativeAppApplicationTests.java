package com.reactiveapp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;



@RunWith(SpringRunner.class)
//@SpringBootTest
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
@WebFluxTest
class ReativeAppApplicationTests {

	
	
	@Test
	void fluxTesting()
	{
		
		Flux.just("A","B").subscribe(e->System.out.println(e));
		Flux<String> stringFlux = Flux.just("A","B")
				.concatWithValues("Good")
				//.concatWith(Flux.error(new RuntimeException("Flux Error")))
				.concatWithValues("Bad")
				.log();
		
		stringFlux.subscribe(System.out::println
				,e->System.err.println("#####Error in Exception####: "+e)
				,()->System.out.println("#####Successs#: "));
		
		
		
	}
	
	@Test
	void fluxWithout_ErrorTesting()
	{
		
		Flux<String> stringFlux = Flux.just("A","B")
				.concatWithValues("Good")
				//.concatWith(Flux.error(new RuntimeException("Flux Error")))
				.concatWithValues("Bad")
				.log();
		

		
		StepVerifier.create(stringFlux)
		.expectNext("A")
		.expectNext("B")
		.expectNext("Good")
		.expectNext("Bad")
		.verifyComplete();
		
		
	}
	
	

	@Test
	void fluxElementsCount()
	{
		
		Flux<String> stringFlux = Flux.just("A","B")
				.concatWithValues("Good")
				.concatWithValues("Bad")
				.log();
		
		
		StepVerifier.create(stringFlux)
		.expectNextCount(4)
		.verifyComplete();
		
		
	}
	
	
	@Test
	void monoElementsCount()
	{
		
		Mono<String> stringFlux = Mono.just("A")
				.log();
		
		
		StepVerifier.create(stringFlux)
		.expectNextCount(1)
		.verifyComplete();
		
		
	}
		
	
	@Autowired
	WebTestClient webClient;
	
	@Test
	void webClient_ElementsCount()
	{
		
		Flux<Integer> stringFlux = webClient.get().uri("/steamFlux")
								.accept(MediaType.APPLICATION_STREAM_JSON)
								.exchange()
								.expectStatus().isOk()
								.returnResult(Integer.class).getResponseBody();
							

		
		
		StepVerifier.create(stringFlux)
		.expectSubscription()
		.expectNextCount(5)
		.verifyComplete();
		
		
	}
	
}
