package com.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ResourceApplication2 {

	
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication2.class, args);
	}
	
	/**
	 * 开启负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	
	
}
