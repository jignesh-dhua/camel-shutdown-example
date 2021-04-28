package com.oup.integration.demo.route;

import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

import com.oup.integration.demo.config.CustomRoutePolicy;

public abstract class OupRouteBuilder extends RouteBuilder {

	
	CustomRoutePolicy customRoutePolicy;
	
	public OupRouteBuilder(CustomRoutePolicy CustomRoutePolicy) {
		this.customRoutePolicy = CustomRoutePolicy;
	}
	
	@Override
	protected void configureRoute(RouteDefinition route) {
		log.info("Configuring route");
		route.routePolicy(customRoutePolicy);
		
		route.shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks);
			
		super.configureRoute(route);
	}
	
}