package io.artisancodes.app.model;

import java.util.Date;

public class Book {
    private long isbn;
    private String title;
    private int stock;
    private Date releaseDate;
    private Category category;
    private Author author;

    public Book() {
    }

    public Book(long isbn, String title, int stock, Date releaseDate, Category category, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.stock = stock;
        this.releaseDate = releaseDate;
        this.category = category;
        this.author = author;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", stock=" + stock +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}
