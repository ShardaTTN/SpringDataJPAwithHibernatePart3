package com.tothenew.sharda;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.tothenew.sharda.Entities.Address;
import com.tothenew.sharda.Entities.Author;
import com.tothenew.sharda.Entities.Book;
import com.tothenew.sharda.Entities.Subject;
import com.tothenew.sharda.Repositories.AuthorRepository;
import com.tothenew.sharda.Repositories.BookRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestEntityManager
class SpringDataJpAwithHibernatePartThreeApplicationTests {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateAuthor() {
		ArrayList<Subject> list = new ArrayList<>();
		list.add(new Subject("DAA"));
		list.add(new Subject("MPOB"));
		list.add(new Subject("AIML"));
		Author author = new Author();
		Address address = new Address();
		address.setStreetnumber("39A");
		address.setLocation("Saharsa");
		address.setState("Bihar");
		author.setName("Sneha");
		author.setAddress(address);
		author.setSubjectlist(list);
	
		authorRepository.save(author);
	}
	
	//Uncomment when want to test @OnetoOne mapping
//	@Test
//    public void testOneToOneCreateBook(){
//        Book book = new Book();
//        book.setBookname("Java");
//        Author author = new Author();
//        author.setName("Sneha");
//        book.setAuthor(author);
//        bookRepository.save(book);
//    }
	
	@Test
	public void testOnetoManyMapping() {
		Author author = new Author();
		author.setName("Sneha");
		
		Book b1 = new Book();
		b1.setBookname("Java - A Complete Reference");
		
		Book b2 = new Book();
		b2.setBookname("Head First - Java");
		
		author.addBook(b1);
		author.addBook(b2);
		
		authorRepository.save(author);
	}
	
	
	@Test
	public void testManyToManyMapping() {
		Author author = new Author();
		author.setName("Sneha");
		
		
		Book b1 = new Book();
		b1.setBookname("Java - A Complete Reference");
		
		
		author.addBook(b1);
		
		authorRepository.save(author);
	}
	
	@Test
	@Transactional
	public void testCaching() {
		authorRepository.findById(1);
		authorRepository.findById(1);
	}
	
	//for level 1 caching to work, we need to mark it with @Transactional from spring package,
	// Spring creates level 1 caching for the Spring session associated with the transaction and for it to work we need to mark it with this annotation.
	//Evict method is used on the session object to remove an object from the cache.
	@Test
	@Transactional
	public void testEvict() {
		Session session = entityManager.unwrap(Session.class);
		Author curr = authorRepository.findById(1).get();
		
		authorRepository.findById(1);
		
		//If comment this part only 1 select query will show in console.
		session.evict(curr);
		
		authorRepository.findById(1);
	}
}
