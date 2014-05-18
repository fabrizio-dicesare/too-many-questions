package toomanyquestions.domain.entities;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This class holds a named address book and operates on it
 *
 */
@Component
public class AddressBook {
	private String name;
	private List<Contact> contacts;
	private ContactsFilterStrategy filter;
	
	public AddressBook(String name, List<Contact> contacts) {
		super();
		this.name = name;
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactsFilterStrategy getFilter() {
		return filter;
	}

	public void setFilter(ContactsFilterStrategy filter) {
		this.filter = filter;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public List<Contact> filterContactsByGender(Contact.Gender gender) {
		return filter.filterByGender(this.contacts, gender);
	}
}
