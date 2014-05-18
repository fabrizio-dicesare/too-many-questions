package toomanyquestions.domain.entities;

import java.util.List;

import org.springframework.stereotype.Component;

import toomanyquestions.domain.entities.Contact.Gender;

/**
 * Strategy for filtering a Contact list
 *
 */
@Component
public interface ContactsFilterStrategy {
	
	/**
	 * Returns a sublist of the given list, containing only the contacts of a given gender 
	 * @param contactsToFilter List of contacts to be filtered
	 * @param gender Gender by which the contacts should be filtered
	 * @return A sublist containing the contacts of the given gender
	 */
	List<Contact> filterByGender(List<Contact> contactsToFilter, Gender gender);
}
