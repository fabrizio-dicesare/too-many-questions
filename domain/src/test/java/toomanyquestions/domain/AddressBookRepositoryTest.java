package toomanyquestions.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toomanyquestions.domain.configuration.DomainConfiguration;
import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.repositories.AddressBookRepository;


/**
 * Unit test for AddressBookRepository.
 */
public class AddressBookRepositoryTest
{
	
	@Test
    public void readAddressBookTest() throws Exception
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Create repository
			AddressBookRepository repository = (AddressBookRepository) ctx.getBean(AddressBookRepository.class);
			
			//read from file
			AddressBook addressBook = repository.readAddressBookByName("AddressBook");
			
			//Assert records number
			assertThat("Address book contacts number", addressBook.getContacts().size(), equalTo(5));
			
			//Assert last contact
			Contact contact = addressBook.getContacts().get(4);
			
			assertThat("Contact name", contact.getFullName(), equalTo("Wes Jackson"));			
						
    	}
    }
}