package toomanyquestions.domain.entities;

import java.util.List;

import toomanyquestions.domain.entities.Contact.Gender;

/**
 * Strategy for filtering a Contact list
 *
 */
public interface ContactsFilterStrategy {
	List<Contact> filterByGender(List<Contact> contactsToFilter, Gender gender);
}
