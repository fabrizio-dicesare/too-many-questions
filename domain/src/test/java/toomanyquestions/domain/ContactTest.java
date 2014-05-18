package toomanyquestions.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toomanyquestions.domain.configuration.DomainConfiguration;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.entities.Contact.Gender;


/**
 * Unit test for Contact.
 */
public class ContactTest
{
	
	@Test
    public void compareContactsTest() throws Exception
    {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			//Create contacts
			Contact olderContact = (Contact) ctx.getBean("contact", "Fabrizio", Gender.MALE, new GregorianCalendar(1978,4,15).getTime());
			Contact youngerContact = (Contact) ctx.getBean("contact", "Emanuela", Gender.FEMALE, new GregorianCalendar(1978,7,27).getTime());
			
			//Assert birth date difference
			int daysBetweenContacts = youngerContact.compareTo(olderContact);
			
			assertThat("Days between contacts", daysBetweenContacts, equalTo(104));						
    	}
    }
}