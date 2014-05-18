package toomanyquestions.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

import toomanyquestions.domain.entities.Contact.Gender;

/**
 * Simple strategy to filter a list of contacts
 */
public class SimpleContactsFilterStrategy implements ContactsFilterStrategy {

	@Override	
	public List<Contact> filterByGender(List<Contact> contactsToFilter, Gender gender) {
		return contactsToFilter.stream().filter(c -> c.getGender() == gender).collect(Collectors.toList());		
	}

	@Override
	public List<Contact> filterByName(List<Contact> contactsToFilter, String name) {
		return contactsToFilter.stream().filter(c -> c.getFullName().equals(name)).collect(Collectors.toList());
	}

}
