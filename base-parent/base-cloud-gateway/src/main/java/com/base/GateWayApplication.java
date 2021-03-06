package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class GateWayApplication {
	
	
	/**
	 * api 接口
	 * http://127.0.0.1:9000/doc.html
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}
	
	/**
	 * 限流
	 * @return
	 */
	@Bean
	public KeyResolver uriKeyResolver() {
		
		return new KeyResolver() { 
			
			@Override
			public Mono<String> resolve(ServerWebExchange exchange) {
				return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
			}
		};
	}
	
	

}
