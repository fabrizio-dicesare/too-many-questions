package toomanyquestions.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toomanyquestions.domain.configuration.DomainConfiguration;
import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.entities.Contact.Gender;


/**
 * Unit test for AddressBook.
 */
public class AddressBookTest
{
	@Test
    public void filterByGenderTest()
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Create contacts
			Contact contactMale = (Contact) ctx.getBean("contact", "Fabrizio", Gender.MALE, new Date());
			Contact contactFemale = (Contact) ctx.getBean("contact", "Emanuela", Gender.FEMALE, new Date());
			Contact contactOtherMale = (Contact) ctx.getBean("contact", "Gianluca", Gender.MALE, new Date());
			
			List <Contact> contacts = new ArrayList<Contact>();
			contacts.add(contactMale);
			contacts.add(contactOtherMale);
			contacts.add(contactFemale);
			
			//Create an address book
			AddressBook addressBook = (AddressBook) ctx.getBean("addressBook", "MyAddressBook", contacts);
			
			//Filter Males
			List<Contact> malesContacts = addressBook.filterContactsByGender(Contact.Gender.MALE);
			
			assertThat("Filtered males", malesContacts.size(), equalTo(2));
			
			//Filter Females
			List<Contact> femalesContacts = addressBook.filterContactsByGender(Contact.Gender.FEMALE);
			
			assertThat("Filtered females", femalesContacts.size(), equalTo(1));
    	}
    }
	
	@Test
    public void sortByDate()
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Create contacts
			Contact contactOne = (Contact) ctx.getBean("contact", "Fabrizio", Gender.MALE, new GregorianCalendar(1978,4,15).getTime());
			Contact contactTwo = (Contact) ctx.getBean("contact", "Emanuela", Gender.FEMALE,  new GregorianCalendar(1978,7,27).getTime());
			Contact contactThree = (Contact) ctx.getBean("contact", "Gianluca", Gender.MALE,  new GregorianCalendar(1976,9,16).getTime());
			
			List <Contact> contacts = new ArrayList<Contact>();
			contacts.add(contactOne);
			contacts.add(contactThree);
			contacts.add(contactTwo);
			
			//Create an address book
			AddressBook addressBook = (AddressBook) ctx.getBean("addressBook", "MyAddressBook", contacts);
			
			//Sort book contacts
			addressBook.sortContactsByDate();
			
			assertThat("After sort size", addressBook.getContacts().size(), equalTo(3));
			
			//Assert contacts order
			assertThat("First contact", addressBook.getContacts().get(0).getFullName(), equalTo("Gianluca"));
			assertThat("Second contact", addressBook.getContacts().get(1).getFullName(), equalTo("Fabrizio"));
			assertThat("Third contact", addressBook.getContacts().get(2).getFullName(), equalTo("Emanuela"));
		}
    }
}
