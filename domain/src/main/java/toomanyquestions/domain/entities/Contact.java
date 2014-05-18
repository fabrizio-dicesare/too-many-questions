package toomanyquestions.domain.entities;

import java.util.Comparator;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This class holds contact data and operates on it
 *
 */
@Component
public class Contact implements Comparable<Contact> {
	
	public enum Gender {MALE, FEMALE}
	
	private String fullName;
	private Gender gender;
	private Date birthDate;
	private Comparator<Contact> comparator;
		
	public Contact(String fullName, Gender gender, Date birthDate) {
		super();
		this.fullName = fullName;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Comparator<Contact> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Contact> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int compareTo(Contact arg0) {
		return comparator.compare(this, arg0);
	}	
}
