package com.learning.linnyk.lmi.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Message {

	private String target;
	private String destination;
	private String body;

	public void setTarget(String target) {
		this.target = target;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTarget() {
		return target;
	}

	public String getDestination() {
		return destination;
	}

	public String getBody() {
		return body;
	}

	void read(){
		System.out.println(hashCode());
	}


}
