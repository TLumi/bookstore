package fi.Lumilahti_palvelinohjelmointi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Book;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.BookRepository;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Category;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.CategoryRepository;



@RunWith(SpringRunner.class)
@DataJpaTest

public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	@Autowired 
	private CategoryRepository crepository;
	
	
	@Test
	public void findByAuthorBookShouldReturnListOfBooks() {
		List<Book> books = repository.findByTitle("Vaellus Suomessa");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("J. Ahola");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("AAA", "BBB", "000-123", 2000, 20.00, new Category("Lapset"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
		
	}
	
	@Test
	public void findByTitleShouldReturnBooks() {
		List<Book> books =repository.findByTitle("Vaellus Suomessa H2");
		assertThat(books).hasSize(1);
	}

	@Test 
	public void deleteBook() {
		 
		List<Book> books = repository.findByTitle("Vaellus Suomessa");
		repository.deleteById(books.get(0).getId());
		books= repository.findByTitle("Vaellus Suomessa");
		assertThat(books).hasSize(0);
	}


	
}
