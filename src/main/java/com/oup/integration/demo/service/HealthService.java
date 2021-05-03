package com.oup.integration.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oup.integration.demo.config.ShutdownManager;

@Service
public class HealthService {
	private boolean healthy = true;

	@Autowired
	private ShutdownManager shutdownManager;
	
	public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}

	public void unhealthy() {
		setHealthy(false);
		shutdownManager.initiateShutdown(0);
		try {
			Thread.sleep(15000L); //wait for 15 seconds
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}