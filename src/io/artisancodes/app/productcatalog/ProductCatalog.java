package io.artisancodes.app.productcatalog;

import io.artisancodes.app.model.Book;

import java.util.List;

public interface ProductCatalog {
    Book findBookByIsbn(long isbn);
    Book findBookByTitle(String title);
    List<Book> findBooksByAuthorName(String authorName);
    void save(Book book);
}
