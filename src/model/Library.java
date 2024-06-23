package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) throws Exception {
        Book bookToRemove = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new Exception("Book not found"));
        books.remove(bookToRemove);
    }

    public void markAsFavorite(String title) throws Exception {
        Book bookToFavorite = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new Exception("Book not found"));
        bookToFavorite.setFavorite(true);
    }

    public List<Book> getFavoriteBooks() {
        return books.stream()
                .filter(Book::isFavorite)
                .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getGenre().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooks() {
        return books;
    }
}
