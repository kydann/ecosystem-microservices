package com.citibanamex.bne.util;
import java.util.Collections;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class ServiceInfo implements InfoContributor{
	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("details",
				Collections.singletonMap("description", "This is the Citi service, which is discovery server aware, and this service will Call Citi Microservice, for citi details, which is again dicovery server aware!!! "));
	}
}
