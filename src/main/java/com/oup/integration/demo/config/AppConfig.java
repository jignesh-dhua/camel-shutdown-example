package com.oup.integration.demo.config;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
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

				context.setAllowUseOriginalMessage(true);

				context.setShutdownStrategy(new CustomShutdownStrategy());

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