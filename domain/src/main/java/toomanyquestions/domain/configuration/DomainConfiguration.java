package toomanyquestions.domain.configuration;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.entities.Contact.Gender;
import toomanyquestions.domain.entities.ContactsFilterStrategy;
import toomanyquestions.domain.entities.SimpleContactsFilterStrategy;

@Configuration
public class DomainConfiguration {
	
	@Bean
	@Scope("prototype")
	public Contact contact(String fullName, Gender gender, Date birthDate) {
		return new Contact(fullName, gender, birthDate);
	}
	
	@Bean
	@Scope("prototype")
	public AddressBook addressBook(String name, List<Contact> contacts) {
		AddressBook addressBook = new AddressBook(name, contacts);
		addressBook.setFilter(contactsFilterStrategy());
		return addressBook;
	}
	
	@Bean
	public ContactsFilterStrategy contactsFilterStrategy() {
		return new SimpleContactsFilterStrategy();
	}
}
