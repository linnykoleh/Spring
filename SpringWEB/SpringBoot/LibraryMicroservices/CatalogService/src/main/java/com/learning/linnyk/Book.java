package com.learning.linnyk;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

	@Id
	private long bookId;
	private String title;
	private int pageCount;
	private String authorFirstName;
	private String authorLastName;

}
