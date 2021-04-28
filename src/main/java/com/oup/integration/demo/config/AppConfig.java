package com.oup.integration.demo.config;

import java.util.Collection;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Component;
import org.apache.camel.Endpoint;
import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.Service;
import org.apache.camel.VetoCamelContextStartException;
import org.apache.camel.spi.LifecycleStrategy;
import org.apache.camel.spi.RouteContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	protected Logger log = LoggerFactory.getLogger(getClass());

	@Bean
	CamelContextConfiguration contextConfiguration() {
		return new CamelContextConfiguration() {
			@Override
			public void beforeApplicationStart(CamelContext context) {

				log.info("################### Before Application Start ############################");
				context.getGlobalOptions().put("http.proxyHost", "ouparray.oup.com");
				context.getGlobalOptions().put("http.proxyPort", "8080");
				
				context.getShutdownStrategy().setLogInflightExchangesOnTimeout(true);
				context.getShutdownStrategy().setTimeUnit(TimeUnit.SECONDS);
				context.getShutdownStrategy().setTimeout(30);
			
				
			}

			@Override
			public void afterApplicationStart(CamelContext camelContext) {
				log.info("################### After Application Start  ############################");
			}
		};
	}
}