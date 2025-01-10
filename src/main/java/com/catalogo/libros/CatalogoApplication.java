package com.catalogo.libros;

import com.catalogo.libros.controller.CommandLineMenu;
import com.catalogo.libros.service.BookService;
import com.catalogo.libros.service.GutendexClient;
import com.catalogo.libros.service.JsonParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	private final CommandLineMenu menu;

	public CatalogoApplication() {
		// Inyectamos manualmente las dependencias
		GutendexClient client = new GutendexClient();
		JsonParser parser = new JsonParser();
		BookService bookService = new BookService();
		this.menu = new CommandLineMenu(client, parser, bookService);
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		menu.showMenu(); // Llamada al menú interactivo
	}
}
