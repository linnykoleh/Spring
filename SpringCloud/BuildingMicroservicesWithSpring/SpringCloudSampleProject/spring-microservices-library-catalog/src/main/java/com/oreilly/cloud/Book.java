package com.oreilly.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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
        final List<Book> tmpBooks = new ArrayList<>();
        int iteration = 0;
        for (Object[] tmpBook : Arrays.asList(books)) {
            tmpBooks.add(new Book(iteration++, tmpBook[0].toString(), tmpBook[1].toString(), tmpBook[2].toString(),
                    new Integer(tmpBook[3].toString()), tmpBook[4].toString()));
        }
        return tmpBooks;
    }

}
