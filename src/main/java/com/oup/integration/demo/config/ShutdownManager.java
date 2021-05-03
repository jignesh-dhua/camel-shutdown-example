package com.oup.integration.demo.config;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ShutdownManager {

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private CamelContext camelContext;

	/*
	 * Invoke with `0` to indicate no error or different code to indicate abnormal
	 * exit. es: shutdownManager.initiateShutdown(0);
	 **/
	@Async
	public void initiateShutdown(int returnCode) {

		try {
			camelContext.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.exit(appContext, () -> returnCode);
	}
}