package toomanyquestions.domain.repositories;

import org.springframework.stereotype.Repository;

import toomanyquestions.domain.entities.AddressBook;

/**
 * Persists address book data
 *
 */
@Repository
public interface AddressBookRepository {
	
	/**
	 * Reads address book data from a persistence provider
	 * @param name Name of the address book to read
	 * @return The requested address book 
	 */
	AddressBook readAddressBookByName(String name) throws Exception;
}
