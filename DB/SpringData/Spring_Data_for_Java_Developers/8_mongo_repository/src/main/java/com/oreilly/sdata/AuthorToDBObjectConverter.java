package com.oreilly.sdata;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AuthorToDBObjectConverter implements Converter<Author, DBObject> {

	@Override
	public DBObject convert(Author source) {
		final DBObject dbObject = new BasicDBObject();

		dbObject.put("name", source.getFirstName() + " " + source.getLastName());
		dbObject.put("country", source.getCountry());

		return dbObject;
	}
}
