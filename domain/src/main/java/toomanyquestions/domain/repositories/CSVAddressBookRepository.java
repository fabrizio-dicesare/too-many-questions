package toomanyquestions.domain.repositories;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import toomanyquestions.domain.configuration.DomainConfiguration;
import toomanyquestions.domain.entities.AddressBook;
import toomanyquestions.domain.entities.Contact;
import au.com.bytecode.opencsv.CSVReader;

/**
 * Repository that persists address book data in a Comma Separated Value (aka CSV) file.
 * 
 */
public class CSVAddressBookRepository implements AddressBookRepository {
	
	private ContactParser parser;
	private String filesPath;
	
	public CSVAddressBookRepository(ContactParser parser, String filesPath) {
		super();
		this.parser = parser;
		this.filesPath = filesPath;
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public ContactParser getParser() {
		return parser;
	}

	public void setParser(ContactParser parser) {
		this.parser = parser;
	}

	@Override
	public AddressBook readAddressBookByName(String name) throws Exception {
		
		//Read csv file
		//Use Spring application context as a simple factory for entities
		try (CSVReader reader = new CSVReader(new FileReader(Paths.get(filesPath, name).toString()));
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DomainConfiguration.class);)
		{
			List<String[]> csvEntries = reader.readAll();
			List<Contact> contacts = new ArrayList<>();
			
			if (csvEntries != null) {
				for (int i = 0; i < csvEntries.size(); i++) {
					try {
						Contact contact = parser.parse(csvEntries.get(i), ctx);
						contacts.add(contact);
					} catch (Exception ex) {
						System.err.println("Error parsing row n. " + (i+1) + ": " + ex.getClass().getName() + " - " + ex.getMessage() + ". See log for details."); //TODO: logging :)					
					}
				}
			}
			
			AddressBook addressBook = (AddressBook) ctx.getBean("addressBook", name, contacts);
			
			return addressBook;			
		}
	}	
}