package com.oup.integration.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oup.integration.demo.service.HealthService;

@RestController
public class HealthController {
	private final HealthService healthService;

	@Autowired
	public HealthController(HealthService healthService) {
		this.healthService = healthService;
	}

	@GetMapping("/unhealthy")
	public void unhealthly() {
		healthService.unhealthy();
	}
}