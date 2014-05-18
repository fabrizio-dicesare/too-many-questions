package toomanyquestions.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
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
}
