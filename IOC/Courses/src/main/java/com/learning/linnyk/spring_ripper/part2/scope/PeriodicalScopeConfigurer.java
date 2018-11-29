package com.learning.linnyk.spring_ripper.part2.scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author LinnykOleh
 */
public class PeriodicalScopeConfigurer implements Scope {

	private Map<String, Pair<LocalTime, Object>> map = new HashMap<>();

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if(map.containsKey(name)){
			final Pair<LocalTime, Object> pair = map.get(name);

			final int secondSinceLastRequest = LocalTime.now().getSecond() - pair.getKey().getSecond();
			if(secondSinceLastRequest > 3){
				map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
			}
		}else {
			map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
		}
		return map.get(name).getValue();
	}

	@Override
	public Object remove(String name) {
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {

	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		return null;
	}
}
