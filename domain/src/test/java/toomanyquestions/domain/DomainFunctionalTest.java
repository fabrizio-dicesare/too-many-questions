package toomanyquestions.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toomanyquestions.domain.configuration.DomainConfiguration;
import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.repositories.AddressBookRepository;


/**
 * Functional tests for the whole domain
 */
public class DomainFunctionalTest
{
	@Test
    public void countMales() throws Exception
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Get the repository from the dependency injection container
			AddressBookRepository repository = ctx.getBean(AddressBookRepository.class);
			
			//Load contacts from csv
			AddressBook addressBook = repository.readAddressBookByName("AddressBook");
			
			//Filter Males
			List<Contact> malesContacts = addressBook.filterContactsByGender(Contact.Gender.MALE);
			
			//Count and assert males
			assertThat("Filtered males count", malesContacts.size(), equalTo(3));
		}
    }
	
	@Test
    public void getOldestContact() throws Exception
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Get the repository from the dependency injection container
			AddressBookRepository repository = ctx.getBean(AddressBookRepository.class);
			
			//Load contacts from csv
			AddressBook addressBook = repository.readAddressBookByName("AddressBook");
			
			//Sort book
			addressBook.sortContactsByDate();
			
			//Get and assert oldest contact
			assertThat("Oldest contact", addressBook.getContacts().get(0).getFullName(), equalTo("Wes Jackson"));
		}
    }
	
	@Test
	public void computeDifferenceBetweenContactsDates() throws Exception
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Get the repository from the dependency injection container
			AddressBookRepository repository = ctx.getBean(AddressBookRepository.class);
			
			//Load contacts from csv
			AddressBook addressBook = repository.readAddressBookByName("AddressBook");
			
			//Get Bill and Paul contacts
			Contact billContact = addressBook.filterContactsByName("Bill McKnight").get(0);
			Contact paulContact = addressBook.filterContactsByName("Paul Robinson").get(0);
			
			//Compute and assert difference
			assertThat("Difference in days between contacts", paulContact.compareTo(billContact), equalTo(2862));
		}
    }
}