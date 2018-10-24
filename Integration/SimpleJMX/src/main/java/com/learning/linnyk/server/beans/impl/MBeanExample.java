package com.learning.linnyk.server.beans.impl;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import com.learning.linnyk.server.beans.IMBeanExample;

@Component
@ManagedResource(objectName = "beans:name=MBeanExample")
public class MBeanExample implements IMBeanExample {

	private int numberPerPage;

	@ManagedAttribute // Экспортирует numberPerPage как управляемый атрибут
	@Override
	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

	@ManagedAttribute // Экспортирует numberPerPage как управляемый атрибут
	@Override
	public int getNumberPerPage() {
		return numberPerPage;
	}

	@ManagedOperation
	public void start() {
		for (int i = 0; i < numberPerPage; i++) {
			System.out.println(i);
		}
	}

}
