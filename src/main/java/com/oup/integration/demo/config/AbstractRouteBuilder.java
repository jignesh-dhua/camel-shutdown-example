package com.oup.integration.demo.config;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public abstract class AbstractRouteBuilder extends RouteBuilder {

	public AbstractRouteBuilder(){
		configureOnCompletion();

	}
	@Override
	protected void configureRoute(RouteDefinition route) {
		log.info("Configuring route");
		
		route.shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks);
			
		super.configureRoute(route);
	}

	private void configureOnCompletion(){

		onCompletion()
			.onWhen(simple("${exchangeProperty.CamelExceptionCaught} == null"))
			.log(LoggingLevel.INFO, log,"Route Execution success GenericRoute onCompletion Block triggered")
			.bean(this.getClass(), "onSuccess");
		
		onCompletion()
			.onWhen(simple("${exchangeProperty.CamelExceptionCaught} != null"))
			.log(LoggingLevel.INFO, log,"Route Execution failure GenericRoute onCompletion Block triggered")
			.bean(this.getClass(), "onFailure");

	}
	public abstract void onSuccess(Exchange exchange);
	public abstract void onFailure(Exchange exchange);
}