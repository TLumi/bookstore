package fi.Lumilahti_palvelinohjelmointi.Bookstore;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Book;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.BookRepository;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Category;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.CategoryRepository;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.User;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository UserRepository) {
		return (args) -> {
			log.info("save categorys and books");
			
			crepository.save(new Category("Lapset"));
			crepository.save(new Category("Ruoka"));
			crepository.save(new Category("Harrastukset"));
			
			System.out.println("save books to db");
			brepository.save(new Book("J. Ahola", "Vaellus Suomessa", " 978-952-321-791-1", 2020, 27.95, crepository.findByName("Harrastukset").get(0) ));
			brepository.save(new Book("M. Kunnas", "Majatalon väki ja kaappikellon kummitukset", " 978-951-1-14424-3", 2012, 19.90, crepository.findByName("Lapset").get(0) ));
			
			System.out.println("fetch all books");
			for (Book book : brepository.findAll()) {
				System.out.println(book.toString());
			}
			
			System.out.println("fetch all categories");
			for (Category category : crepository.findAll()) {
				System.out.println(category.getName());
			}
			
			User user1 = new User("user","$2a$10$472N8IHlMHj4YL5pfWZ6PuAe0ASYuxgnhqh49kT8BVkAGbj5YdVpK", "user@user.fi", "USER");
			User user2 = new User("admin","$2a$10$5K7KhHyK1xwGpJEej2VHEOTVnTpI9t6y4ECBgsweJ4CWgT/SJeB3e", "admin@admin.fi", "ADMIN");
			UserRepository.save(user1);
			UserRepository.save(user2);
		};
	}

}
