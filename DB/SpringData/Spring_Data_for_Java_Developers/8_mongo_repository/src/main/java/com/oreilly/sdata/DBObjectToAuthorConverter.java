package com.oreilly.sdata;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

public class DBObjectToAuthorConverter implements Converter<DBObject, Author> {

	@Override
	public Author convert(DBObject source) {
		final Author author = new Author();

		author.setCountry(source.get("country").toString().split(" ")[0]);
		author.setFirstName(source.get("name").toString().split(" ")[0]);
		author.setLastName(source.get("name").toString().split(" ")[1]);

		return author;
	}
}
