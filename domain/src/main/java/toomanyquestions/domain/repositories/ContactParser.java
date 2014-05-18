package toomanyquestions.domain.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import toomanyquestions.domain.entities.Contact;

/**
 * Parses raw data to create a contact
 *
 */
@Component
public interface ContactParser {
	
	/**
	 * Parses string values to create a contact
	 * @param stringValues Values containing string data
	 * @param ctx Spring application context (used as a simple Factory for entities)
	 * @return An instance of Contact filled with the given data
	 */
	Contact parse(String[] stringValues, ApplicationContext ctx) throws Exception;
}
