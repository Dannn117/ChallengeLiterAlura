package com.catalogo.libros.service;

import com.catalogo.libros.model.Author;
import com.catalogo.libros.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class    BookService {

    private final List<Book> books = new ArrayList<>();

    public void addBooks(List<Book> newBooks) {
        books.addAll(newBooks);
    }

    public void displayBooks(List<Book> booksToDisplay) {
        if (booksToDisplay.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            booksToDisplay.forEach(System.out::println);
        }
    }

    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void listAllAuthors() {
        var authors = books.stream()
                .flatMap(book -> book.getAuthor().stream()) // Desplegar lista de autores
                .map(Author::getName) // Obtener nombres de autores
                .distinct()
                .collect(Collectors.toList());

        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    public void listAuthorsAliveInYear(int year) {
        var authors = books.stream()
                .flatMap(book -> book.getAuthor().stream()) // Desplegar lista de autores
                .filter(author -> (author.getBirthYear() <= year) &&
                        (author.getDeathYear() == null || author.getDeathYear() > year))
                .map(Author::getName)
                .distinct()
                .collect(Collectors.toList());

        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year);
        } else {
            authors.forEach(System.out::println);
        }
    }


    public void listBooksByLanguage(String language) {
        var filteredBooks = books.stream()
                .filter(book -> book.getLanguages().contains(language))
                .collect(Collectors.toList());
        displayBooks(filteredBooks);
    }
}
