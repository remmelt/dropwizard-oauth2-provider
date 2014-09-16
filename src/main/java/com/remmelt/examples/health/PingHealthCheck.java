package com.remmelt.examples.health;

import com.codahale.metrics.health.HealthCheck;
import com.remmelt.examples.resources.PingResource;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PingHealthCheck extends HealthCheck {
	private PingResource pingResource;

	@Override
	protected Result check() throws Exception {
		String s = pingResource.pongAuthenticated(12l);
		if (s.contains("12")) {
			return Result.healthy();
		}
		return Result.unhealthy("Auth ping should contain user id");
	}
}
