package toomanyquestions.domain.entities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple strategy to sort a list of contacts
 */
public class SimpleContactsSortStrategy implements ContactsSortStrategy {
	
	private Comparator<Contact> comparator;
	
	public Comparator<Contact> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Contact> comparator) {
		this.comparator = comparator;
	}

	@Override	
	public List<Contact> sortByDate(List<Contact> contactsToSort) {
		return contactsToSort.stream().sorted(comparator).collect(Collectors.toList());		
	}
}