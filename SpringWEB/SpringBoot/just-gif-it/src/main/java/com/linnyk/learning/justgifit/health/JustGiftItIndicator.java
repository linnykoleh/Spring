package com.linnyk.learning.justgifit.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.linnyk.learning.justgifit.properties.JustGifItProperties;

@Component
public class JustGiftItIndicator implements HealthIndicator {

	private final JustGifItProperties justGifItProperties;

	@Autowired
	public JustGiftItIndicator(JustGifItProperties justGifItProperties) {
		this.justGifItProperties = justGifItProperties;
	}

	@Override
	public Health health() {
		if(!justGifItProperties.getGifLocation().canWrite()){
			return Health.down().build();
		}
		return Health.up().build();
	}
}
