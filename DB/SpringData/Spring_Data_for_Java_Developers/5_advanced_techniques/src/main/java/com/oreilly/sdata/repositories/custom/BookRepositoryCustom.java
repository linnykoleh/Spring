package com.oreilly.sdata.repositories.custom;

import com.oreilly.sdata.data.entities.Book;

public interface BookRepositoryCustom {

	void saveAndLog(Book book);

}
