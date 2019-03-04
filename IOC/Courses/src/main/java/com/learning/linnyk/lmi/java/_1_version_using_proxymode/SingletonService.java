package com.learning.linnyk.lmi.java._1_version_using_proxymode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonService {

	private PrototypeService prototypeService;

	@Autowired
	public SingletonService(PrototypeService prototypeService) {
		this.prototypeService = prototypeService;
	}

	public void doIt() {
		System.out.println("SingletonService: " + this.hashCode());
		prototypeService.doInPrototype();
	}
}
