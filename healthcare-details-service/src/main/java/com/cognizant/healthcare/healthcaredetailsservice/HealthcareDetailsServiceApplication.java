package com.cognizant.healthcare.healthcaredetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
public class HealthcareDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareDetailsServiceApplication.class, args);
	}

}
