package toomanyquestions.domain.repositories;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import toomanyquestions.domain.entities.Contact;
import toomanyquestions.domain.entities.Contact.Gender;

public class CSVContactParser implements ContactParser {
	
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

	@Override
	public Contact parse(String[] stringValues, ApplicationContext ctx) throws Exception {		
		Gender gender = Enum.valueOf(Contact.Gender.class, stringValues[1].trim().toUpperCase());
		Date birthDate = dateFormat.parse(stringValues[2].trim());
		Contact contact =  (Contact) ctx.getBean("contact", stringValues[0].trim(), gender, birthDate);
		
		return contact;		
	}

}
