package com.citibanamex.bne.phrase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class phraseapi {
	
	@Autowired
	public Stringsint strinsin;
	
	@HystrixCommand(fallbackMethod="getDefaultMessage")
	public String home() {
		return strinsin.home();
	}
	
}
