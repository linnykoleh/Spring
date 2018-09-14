package com.oreilly.sdata;

import com.oreilly.sdata.repositories.BookRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AsyncBookRepositoryApplication {

    public static void main(String[] args) {
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/application-context.xml");
        final BookRepository bookRepository = context.getBean(BookRepository.class);

        for (long i = 0; i < 4; i++) {
            bookRepository.findByIdsAsync(i);
        }
        //2155
        //6784
        //274
        //3151
        //
        //Execution for ids: [0]Thread: executor-1
        // select * from BOOK b where b.BOOK_ID in (?)
        //Execution for ids: [3]Thread: executor-4
        // select * from BOOK b where b.BOOK_ID in (?)
        // select * where a.AUTHOR_ID=?
        //Execution for ids: [1]Thread: executor-2
        // select * from BOOK b where b.BOOK_ID in (?)
        // select * where a.AUTHOR_ID=?
        //Execution for ids: [2]Thread: executor-3
        // select * from BOOK b where b.BOOK_ID in (?)
        // select * where a.AUTHOR_ID=?
    }

}
