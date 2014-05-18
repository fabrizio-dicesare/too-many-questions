package toomanyquestions.domain.entities;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Strategy for sorting a Contact list
 *
 */
@Component
public interface ContactsSortStrategy {
	
	/**
	 * Sorts a list of contacts by date 
	 * @param contactsToSort List of contacts to be sorted
	 * @param gender Gender by which the contacts should be filtered
	 * @return A sortedList
	 */
	List<Contact> sortByDate(List<Contact> contactsToSort);
}
