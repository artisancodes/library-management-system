package io.artisancodes.app.productcatalog;

import io.artisancodes.app.exception.ResourceNotFoundException;
import io.artisancodes.app.model.Author;
import io.artisancodes.app.model.Book;
import io.artisancodes.app.model.Category;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductCatalogImplTest {

    private static final Author author = new Author(
            "Robert C. Martin",
            "Robert Cecil Martin, colloquially called Uncle Bob, is an American software engineer, instructor, and author");

    private final static Book cleanCode = new Book(
            9780136083252L,
            "Clean Code",
            10,
            new Date("2008/08/01"),
            Category.ENGINEER,
            author);

    private static final Book cleanArchitecture = new Book(
            9780134494166L,
            "Clean Architecture",
            15,
            new Date("2017/09/01"),
            Category.ENGINEER,
            author);

    private final ProductCatalogImpl underTest = new ProductCatalogImpl();

    @Test
    void itShouldFindBookByIsbnSuccessfully() {
        // GIVEN
        underTest.save(cleanCode);
        underTest.save(cleanArchitecture);

        // WHEN
        Book book = underTest.findBookByIsbn(9780136083252L);

        // THEN
        assertNotNull(book);
        assertEquals(cleanCode.getIsbn(), book.getIsbn());
        assertEquals(cleanCode.getTitle(), book.getTitle());
        assertEquals(cleanCode.getStock(), book.getStock());
        assertEquals(0, cleanCode.getReleaseDate().compareTo(book.getReleaseDate()));
        assertNotNull(book.getCategory());
        assertSame(cleanCode.getCategory(), book.getCategory());
        assertNotNull(book.getAuthor());
        assertEquals(author.getName(), book.getAuthor().getName());
        assertEquals(author.getDescription(), book.getAuthor().getDescription());
    }

    @Test
    void itShouldThrowWhenBookIsNotFoundByIsbn() {
        // GIVEN
        underTest.save(cleanCode);
        underTest.save(cleanArchitecture);

        // WHEN
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> underTest.findBookByIsbn(1L));

        // THEN
        assertNotNull(exception);
        assertEquals("Book Not Found - ISBN CODE: 1", exception.getMessage());
    }

    @Test
    void itShouldFindBookByTitleSuccessfully() {
        // GIVEN
        underTest.save(cleanCode);
        underTest.save(cleanArchitecture);

        // WHEN
        Book book = underTest.findBookByTitle("Clean Architecture");

        // THEN
        assertNotNull(book);
        assertEquals("Clean Architecture", book.getTitle());
    }

    @Test
    void itShouldThrowWhenBookIsNotFoundByTitle() {
        // GIVEN
        underTest.save(cleanCode);
        underTest.save(cleanArchitecture);

        // WHEN
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> underTest.findBookByTitle("Dirty Code"));

        // THEN
        assertNotNull(exception);
        assertEquals("Book Not Found - Title: Dirty Code", exception.getMessage());
    }

    @Test
    void itShouldSaveBookAndAuthorSuccessfully() {
        // GIVEN
        long isbn = cleanCode.getIsbn();

        // WHEN
        underTest.save(cleanCode);

        // THEN
        Book book = underTest.findBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());

        Author author = book.getAuthor();
        String authorName = author.getName();
        List<Book> books = underTest.findBooksByAuthorName(authorName);
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    void itShouldThrowWhenFindBooksByAuthorName() {
        // GIVEN
        underTest.save(cleanCode);
        underTest.save(cleanArchitecture);

        // WHEN
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> underTest.findBooksByAuthorName("Rúben Leonardo"));

        // THEN
        assertNotNull(exception);
        assertEquals("Author Not Found - Name: Rúben Leonardo", exception.getMessage());
    }
}