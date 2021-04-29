package com.oup.integration.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck extends AbstractHealthIndicator {
	@Autowired
	private HealthService healthService;

	@Override
	protected void doHealthCheck(Health.Builder health) {
		if (healthService.isHealthy()) {
			health.up();
		} else {
			health.down();
		}
	}
}