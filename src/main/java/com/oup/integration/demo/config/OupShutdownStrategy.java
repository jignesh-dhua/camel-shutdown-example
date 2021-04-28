package com.oup.integration.demo.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Service;
import org.apache.camel.spi.RouteStartupOrder;
import org.apache.camel.spi.ShutdownStrategy;

public class OupShutdownStrategy implements ShutdownStrategy{

	@Override
	public void start() throws Exception {
		
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdownForced(CamelContext context, List<RouteStartupOrder> routes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown(CamelContext context, List<RouteStartupOrder> routes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspend(CamelContext context, List<RouteStartupOrder> routes) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown(CamelContext context, List<RouteStartupOrder> routes, long timeout, TimeUnit timeUnit)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shutdown(CamelContext context, RouteStartupOrder route, long timeout, TimeUnit timeUnit,
			boolean abortAfterTimeout) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void suspend(CamelContext context, List<RouteStartupOrder> routes, long timeout, TimeUnit timeUnit)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTimeout(long timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTimeUnit(TimeUnit timeUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TimeUnit getTimeUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSuppressLoggingOnTimeout(boolean suppressLoggingOnTimeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSuppressLoggingOnTimeout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setShutdownNowOnTimeout(boolean shutdownNowOnTimeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isShutdownNowOnTimeout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setShutdownRoutesInReverseOrder(boolean shutdownRoutesInReverseOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isShutdownRoutesInReverseOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLogInflightExchangesOnTimeout(boolean logInflightExchangesOnTimeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLogInflightExchangesOnTimeout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean forceShutdown(Service service) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasTimeoutOccurred() {
		// TODO Auto-generated method stub
		return false;
	}

}
