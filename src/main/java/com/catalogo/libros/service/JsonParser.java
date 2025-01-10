package com.catalogo.libros.service;

import com.catalogo.libros.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonParser {

    public List<Book> parseBooks(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return List.of(mapper.readValue(json, Book[].class));
    }
}
