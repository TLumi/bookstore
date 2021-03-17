package fi.Lumilahti_palvelinohjelmointi.Bookstore;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.Category;
import fi.Lumilahti_palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

		@Autowired
		CategoryRepository crepository;
	
		@Test
		public void findByCategoryNameShouldReturnCategoryId() {
			List<Category> categories = crepository.findByName("Harrastukset");
			assertThat(categories.get(0).getCategoryId()).isEqualTo(3);
		}

		@Test
		public void createNewCategory() {
			Category category = new Category("Testaus");
			crepository.save(category);
			assertThat(category.getCategoryId()).isNotNull();
		}

		
}
