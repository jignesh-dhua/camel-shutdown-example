package com.oup.integration.demo.route;

import java.util.Collection;

import org.apache.camel.spi.InflightRepository.InflightExchange;
import org.springframework.stereotype.Component;

import com.oup.integration.demo.config.CustomRoutePolicy;

@Component
public class TimerRoute extends OupRouteBuilder {

	
	public TimerRoute(CustomRoutePolicy CustomRoutePolicy) {
		super(CustomRoutePolicy);
	}
	
	@Override
	public void configure() throws Exception {

		onCompletion().process(e->{
			 Collection<InflightExchange> inflightExchanges = e.getContext().getInflightRepository().browse();
			 for (InflightExchange inflightExchange : inflightExchanges) {
				log.info("----- Inflight messages::" + inflightExchange.getExchange().getIn().getBody());
			}
		});
		
		from("timer://test123?repeatCount=1")
		
		
		
		.routeId(getClass().getName())
		.setBody(constant("Hello World"))
		.log("*** Timer started ***")
		.delay(500000)
		//.throwException(new Exception("Something wrong"))
		
		.to("file://C:/Test")
		.log("*** Done ***");
		
	}
}