package fi.Lumilahti_palvelinohjelmointi.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Book;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			System.out.println("save books to db");
			repository.save(new Book("J. Ahola", "Vaellus Suomessa", " 978-952-321-791-1", 2020, 27.95 ));
			repository.save(new Book("M. Kunnas", "Majatalon väki ja kaappikellon kummitukset", " 978-951-1-14424-3", 2012, 19.90 ));
			
			System.out.println("fetch all book");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}

}
