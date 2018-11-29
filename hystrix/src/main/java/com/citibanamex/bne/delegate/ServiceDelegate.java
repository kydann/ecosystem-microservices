package com.citibanamex.bne.delegate;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "callCitiServiceAndGetData_Fallback")
	public String callCitiServiceAndGetData(String employee) {
		System.out.println("Getting Citi details for " + employee);
		String response = restTemplate
				.exchange("http://localhost:7777/v1/getEmployeeDetailsForCiti/{employee}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, employee).getBody();

		System.out.println("Respuesta recibida como " + response + " -  " + new Date());

		return "NORMAL FLOW !!! - Citi: -  " + employee + " :::  Employee Details " + response + " -  " + new Date();
	}
	
	@SuppressWarnings("unused")
	private String callCitiServiceAndGetData_Fallback(String employee) {
		System.out.println("Citi Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!!No Response From Citi Service at this moment. Service will be back shortly - " + new Date();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
