package com.catalogo.libros.controller;

import com.catalogo.libros.service.GutendexClient;
import com.catalogo.libros.service.JsonParser;
import com.catalogo.libros.service.BookService;

import java.util.Scanner;

public class CommandLineMenu {

    private final GutendexClient client;
    private final JsonParser parser;
    private final BookService bookService;

    public CommandLineMenu(GutendexClient client, JsonParser parser, BookService bookService) {
        this.client = client;
        this.parser = parser;
        this.bookService = bookService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo(scanner);
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos(scanner);
                    break;
                case 5:
                    listarLibrosPorIdioma(scanner);
                    break;
                case 6:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        try {
            String jsonResponse = client.fetchBooksByTitle(title);
            var books = parser.parseBooks(jsonResponse);
            bookService.displayBooks(books);
        } catch (Exception e) {
            System.err.println("Error al buscar libros: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        bookService.listAllBooks();
    }

    private void listarAutoresRegistrados() {
        bookService.listAllAuthors();
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese el año para listar autores vivos: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        bookService.listAuthorsAliveInYear(year);
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma para listar libros (ejemplo: 'en', 'es'): ");
        String language = scanner.nextLine();
        bookService.listBooksByLanguage(language);
    }
}
