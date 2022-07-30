package com.reactiveapp.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactiveapp.handler.SampleHandlerFunction;

@Configuration
public class RouterFunctionConfig {
	
	
	
	
	@Bean
	public RouterFunction<ServerResponse> route(SampleHandlerFunction  handlerFunction)
	{
		
		return RouterFunctions
				.route(RequestPredicates.GET("/functional/flux"),handlerFunction::flux)
				.andRoute(RequestPredicates.GET("/functional/mono"),handlerFunction::mono);
		
		
			 
	}

}
