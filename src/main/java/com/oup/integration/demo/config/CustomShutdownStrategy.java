package com.oup.integration.demo.config;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultShutdownStrategy;
import org.apache.camel.spi.RouteStartupOrder;
import org.apache.camel.spi.InflightRepository.InflightExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomShutdownStrategy extends DefaultShutdownStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(CustomShutdownStrategy.class);

	@Override
	protected boolean doShutdown(CamelContext context, List<RouteStartupOrder> routes, long timeout, TimeUnit timeUnit,
			boolean suspendOnly, boolean abortAfterTimeout, boolean forceShutdown) throws Exception {

		Collection<InflightExchange> inflightExchanges = context.getInflightRepository().browse();

		for (InflightExchange inflightExchange : inflightExchanges) {
			handleInflightExchange(inflightExchange);
		}

		LOG.info("*** doShutdown() started ***");
		return super.doShutdown(context, routes, timeout, timeUnit, suspendOnly, abortAfterTimeout, forceShutdown);
	}

	public void handleInflightExchange(InflightExchange inflightExchange) {
		LOG.info("*** Handle Inflight messages: " + inflightExchange.getExchange().getIn().getBody());
	}
}