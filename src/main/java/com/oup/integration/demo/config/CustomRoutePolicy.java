package com.oup.integration.demo.config;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.support.RoutePolicySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomRoutePolicy extends RoutePolicySupport implements CamelContextAware {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private CamelContext camelContext;

	@Autowired
	ShutdownManager shutdownManager;

	@Override
	public void onExchangeBegin(Route route, Exchange exchange) {
		log.info("STARTED PROCESSING MESSAGES/FILES");
		super.onExchangeBegin(route, exchange);
	}

	@Override
	public void onExchangeDone(Route route, Exchange exchange) {
		Object exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		if (exception != null || exchange.getException() != null) {
			log.error("FINISHED PROCESSING MESSAGES/FILES: FAILURE");
			shutdownManager.initiateShutdown(1);

		} else {
			log.info("FINISHED PROCESSING MESSAGES/FILES: SUCCESS");
			shutdownManager.initiateShutdown(0);
		}

		super.onExchangeDone(route, exchange);
	}
	@Override
	public void onStart(Route route) {
		log.info("#### Starting the route ####");
		super.onStart(route);
	}
	@Override
	public void shutdown() throws Exception {
		log.info("#### Shutdown ####");
		super.shutdown();
	}
	
	@Override
	public void onStop(Route route) {
		log.info("#### Stoping the route ####");
		super.onStop(route);
	}

	@Override
	public void setCamelContext(CamelContext camelContext) {
		this.camelContext = camelContext;
	}

	@Override
	public CamelContext getCamelContext() {

		return camelContext;
	}
}