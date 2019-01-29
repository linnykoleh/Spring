package com.example.methodsecurity.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Message {

	@Id
	@GeneratedValue
	private Long id;

	private String text;

	@OneToOne
	private User to;

	@CreatedBy
	private String createdBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public Message(String text, User to) {
		this.text = text;
		this.to = to;
	}

}
