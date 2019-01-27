package com.example.bootcamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Bar {

	private final Foo foo;

	private final Log log = LogFactory.getLog(getClass());

	Bar(Foo foo, @Value("#{ uuid.buildUuid()}") String uuid,
			@Value("#{  2 > 1 }") boolean proceed) {
		this.foo = foo;
		this.log.info("UUID: " + uuid);
		this.log.info("proceed: " + proceed);
	}

}
