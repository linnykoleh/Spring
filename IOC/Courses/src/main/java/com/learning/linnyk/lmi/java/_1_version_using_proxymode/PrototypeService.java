package com.learning.linnyk.lmi.java._1_version_using_proxymode;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrototypeService {

	public void doInPrototype() {
		System.out.println("PrototypeService: " + this.hashCode());
	}
}
