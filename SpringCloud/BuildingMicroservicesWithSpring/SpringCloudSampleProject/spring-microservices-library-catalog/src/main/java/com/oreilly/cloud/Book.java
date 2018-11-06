package com.oreilly.cloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book {

	private int bookId;

	private String title;

	private String authorFirstName;

	private String authorLastName;

	private int pages;

	private String genre;

	private static Object[][] books = {{"To Kill a Mockingbird", "Harper", "Lee", 200, "Fiction"},
			{"A Tale of Two Cities", "Charles", "Dickens", 175, "Fiction"},
			{"Of Mice and Men", "John", "Steinbeck", 100, "Fiction"}, {"Dracula", "Bram", "Stoker", 174, "Fiction"},
			{"The Call of the Wild", "Jack", "London", 225, "Fiction"},
			{"Ulysses", "James", "Joyce", 150, "Fiction"}};

	public static List<Book> getBooks() {
		List<Book> tmpBooks = new ArrayList<>();
		int iteration = 0;
		for (Object[] tmpBook : Arrays.asList(books)) {
			tmpBooks.add(new Book(iteration++, tmpBook[0].toString(), tmpBook[1].toString(), tmpBook[2].toString(),
					new Integer(tmpBook[3].toString()), tmpBook[4].toString()));
		}
		return tmpBooks;
	}

	public Book(int bookId, String title, String authorFirstName, String authorLastName, int pages, String genre) {
		this.bookId = bookId;
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.pages = pages;
		this.genre = genre;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
