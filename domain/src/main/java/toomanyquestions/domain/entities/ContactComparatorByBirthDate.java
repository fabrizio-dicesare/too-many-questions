package toomanyquestions.domain.entities;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Compares two contacts by birth date
 */
public class ContactComparatorByBirthDate implements Comparator<Contact> {

	@Override
	public int compare(Contact arg0, Contact arg1) {
		long diffInMillies = arg0.getBirthDate().getTime() - arg1.getBirthDate().getTime();
		int days = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    return days;
	}

}
