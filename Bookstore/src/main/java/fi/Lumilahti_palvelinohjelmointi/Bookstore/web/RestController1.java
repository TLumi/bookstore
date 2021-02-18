package fi.Lumilahti_palvelinohjelmointi.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Book;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.BookRepository;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Category;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@RestController


public class RestController1 {
	
		@Autowired
		private BookRepository repository;
		@Autowired
		private CategoryRepository crepository;
		
		//palauta kaikki kirjat
				
		@RequestMapping(value="/kirjat", method = RequestMethod.GET)
		public @ResponseBody List<Book> bookListRest() {	
		        return (List<Book>) repository.findAll();
		    } 
		
	
		//palauttaa kaikki kategoriat.. toinen tapa kirjoitaa
		
		@RequestMapping("/kategoriat")
		public List<Category> categoryListRest() {
			return (List) crepository.findAll();
		}

			//palauttaa kirjan id:n perusteella
		
		@RequestMapping(value="/kirja/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	        return repository.findById(bookId);
		    } 
		
}
