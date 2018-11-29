package com.citibanamex.bne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.citibanamex.bne.delegate.*;


@RestController
public class citiapicontroller {
	
	@Autowired
	ServiceDelegate delegate;
	
	@RequestMapping(value = "/getDetails/{employee}", method = RequestMethod.GET)
	public String getEmployees(@PathVariable String employee) {
		System.out.println("¡Voy a llamar al servicio de citi para obtener datos!");
		return delegate.callCitiServiceAndGetData(employee);
	}

}
