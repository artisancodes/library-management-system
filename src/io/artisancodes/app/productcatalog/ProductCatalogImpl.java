package io.artisancodes.app.productcatalog;

import io.artisancodes.app.exception.ResourceNotFoundException;
import io.artisancodes.app.model.Author;
import io.artisancodes.app.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalogImpl implements ProductCatalog {
    private static final List<Book> books = new ArrayList<>();
    private final Map<Author, List<Book>> authorBooks = new HashMap<>();

    @Override
    public Book findBookByIsbn(long isbn) {
        return books.stream()
                .filter(book -> book.getIsbn() == isbn)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found - ISBN CODE: " + isbn));
    }

    @Override
    public Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Book Not Found - Title: " + title));
    }

    @Override
    public List<Book> findBooksByAuthorName(String authorName) {
        Author author = authorBooks.keySet().stream()
                .filter(a -> a.getName().equals(authorName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Author Not Found - Name: " + authorName));

        return authorBooks.get(author);
    }

    @Override
    public void save(Book book) {
        Author author = book.getAuthor();
        if (authorBooks.containsKey(author)) {
            authorBooks.get(author).add(book);
        } else {
            authorBooks.put(author, new ArrayList<>(List.of(book)));
        }
        books.add(book);
    }
}
