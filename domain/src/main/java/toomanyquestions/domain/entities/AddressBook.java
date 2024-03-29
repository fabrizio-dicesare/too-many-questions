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
	private ContactsSortStrategy sort;
	
	public AddressBook(String name, List<Contact> contacts) {
		super();
		this.name = name;
		this.contacts = contacts;
	}
	
	public ContactsSortStrategy getSort() {
		return sort;
	}

	public void setSort(ContactsSortStrategy sort) {
		this.sort = sort;
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
	
	public List<Contact> filterContactsByName(String name) {
		return filter.filterByName(this.contacts, name);
	}
	
	public void sortContactsByDate() {
		this.contacts = sort.sortByDate(this.contacts);
	}
}