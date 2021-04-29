package com.oup.integration.demo.route;

import com.oup.integration.demo.config.AbstractRouteBuilder;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends AbstractRouteBuilder {

	
	@Override
	public void configure() throws Exception {

		onException(IllegalAccessException.class)
			.log("*** onException() *** ");

		from("timer://foo?repeatCount=1")
			.routeId(getClass().getName())
			.setBody(constant("Hello World"))
			.log("*** Timer started ***")
			.delay(5000)
		
			.throwException(new IllegalAccessException("Something is wrong"))		
		
			.to("file://Test")
			.log("*** Done ***");
		
	}

	@Override
	public void onSuccess(Exchange exchange) {
		log.info("*** onSuccess() ***");
	}

	@Override
	public void onFailure(Exchange exchange) {
		log.info("*** onFailure() ***");
	}
}