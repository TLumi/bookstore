package fi.Lumilahti_palvelinohjelmointi.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Book;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.BookRepository;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@Controller


public class BookController {
	
	
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	
	//sisäänkirjautumissivu
	@RequestMapping(value= "/login")
	public String login() {
		return"login";
	}
	
    @RequestMapping(value= {"/", "/booklist"})
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
    	return "addBook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }   
    
        
    @RequestMapping(value = "/edit/{id}")
    public String addBook(@PathVariable("id") Long bookId, Model model){
    model.addAttribute("book", repository.findById(bookId));
    model.addAttribute("categories",  crepository.findAll());
    
    return "editbook";
    }

}



