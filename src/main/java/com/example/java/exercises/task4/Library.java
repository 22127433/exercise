package com.example.java.exercises.task4;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // them book
    public void addBook(Book book) {
        books.add(book);
    }

    // muon book
    public boolean borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && book.isAvailable()) {
                book.setAvailable(false);
                return true;
            }
        }

        return false;
    }
    public boolean borrowBook(Book book) {
        if (books.contains(book) && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    // tra sach
    public boolean returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && !book.isAvailable()) {
                book.setAvailable(true);
                return true;
            }
        }

        return false;
    }
    public boolean returnBook(Book book) {
        if (books.contains(book) && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    // Tim kiem theo ten sach hoac tac gia
    public Book getBook(String query) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())
             || book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                return book;
            }
        }
        return null;
    }
}
