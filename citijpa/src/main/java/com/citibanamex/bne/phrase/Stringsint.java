package com.citibanamex.bne.phrase;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("zuulserver")
public interface Stringsint {
	
    @RequestMapping(method = RequestMethod.GET, value = "/microservicestring")
	public String home();

}
