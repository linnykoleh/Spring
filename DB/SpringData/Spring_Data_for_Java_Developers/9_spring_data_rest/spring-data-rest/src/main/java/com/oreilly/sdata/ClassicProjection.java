package main.java.com.oreilly.sdata;

import com.oreilly.sdata.Book;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "classics", types = {Book.class})
public interface ClassicProjection {

    String getTitle();

    int getPageCount();
}
