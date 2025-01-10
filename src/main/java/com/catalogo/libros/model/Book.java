package com.catalogo.libros.model;

import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private List<String> languages;
    private Author author;
    private int downloadCount;

    // Getters, Setters, toString()

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Título: " + title + "\nAutor: " + authors.get(0).getName() +
                "\nIdiomas: " + languages + "\nDescargas: " + downloadCount;
    }
}
