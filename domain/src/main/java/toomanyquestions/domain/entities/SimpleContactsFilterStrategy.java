package toomanyquestions.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import toomanyquestions.domain.entities.Contact.Gender;

@Component
public class SimpleContactsFilterStrategy implements ContactsFilterStrategy {

	@Override
	public List<Contact> filterByGender(List<Contact> contactsToFilter, Gender gender) {
		return contactsToFilter.stream().filter(c -> c.getGender() == Contact.Gender.MALE).collect(Collectors.toList());		
	}

}
