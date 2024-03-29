package toomanyquestions.domain.configuration;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.entities.Contact.Gender;
import toomanyquestions.domain.entities.ContactComparatorByBirthDate;
import toomanyquestions.domain.entities.ContactsFilterStrategy;
import toomanyquestions.domain.entities.ContactsSortStrategy;
import toomanyquestions.domain.entities.SimpleContactsFilterStrategy;
import toomanyquestions.domain.entities.SimpleContactsSortStrategy;
import toomanyquestions.domain.repositories.AddressBookRepository;
import toomanyquestions.domain.repositories.CSVAddressBookRepository;
import toomanyquestions.domain.repositories.CSVContactParser;
import toomanyquestions.domain.repositories.ContactParser;

@Configuration
public class DomainConfiguration {
	
	@Bean
	@Scope("prototype")
	public Contact contact(String fullName, Gender gender, Date birthDate) {
		Contact contact = new Contact(fullName, gender, birthDate);
		contact.setComparator(contactComparator());
		return contact;
	}
	
	@Bean
	@Scope("prototype")
	public AddressBook addressBook(String name, List<Contact> contacts) {
		AddressBook addressBook = new AddressBook(name, contacts);
		addressBook.setFilter(contactsFilterStrategy());
		addressBook.setSort(contactsSortStrategy());
		return addressBook;
	}
	
	@Bean
	public ContactsFilterStrategy contactsFilterStrategy() {
		return new SimpleContactsFilterStrategy();
	}
	
	@Bean
	public ContactsSortStrategy contactsSortStrategy() {
		SimpleContactsSortStrategy contactsSortStrategy = new SimpleContactsSortStrategy();
		contactsSortStrategy.setComparator(contactComparator());
		return contactsSortStrategy;
	}
	
	@Bean
	public Comparator<Contact> contactComparator() {
		return new ContactComparatorByBirthDate();
	}
	
	@Bean 
	public AddressBookRepository addressBookRepository() {		
		return new CSVAddressBookRepository(contactParser(), "C:\\AddressBooks");
	}
	
	@Bean 
	public ContactParser contactParser() {
		return new CSVContactParser();
	}
}
