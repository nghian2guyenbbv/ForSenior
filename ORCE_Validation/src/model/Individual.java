package model;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Individual")
@XmlAccessorType(XmlAccessType.FIELD)
public class Individual {
	
	@XmlElementWrapper(name = "Name")
	@XmlElement(name = "Name")
	private List<Name> Name;
	
	@XmlElement(name = "ContactInformation")
	private ContactInformation ContactInformation;
	
	@XmlElement(name = "PersonalSummary")
	private PersonalSummary PersonalSummary;

	public List<Name> getName() {
		return Name;
	}

	public void setName(List<Name> name) {
		Name = name;
	}

	public ContactInformation getContactInformation() {
		return ContactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		ContactInformation = contactInformation;
	}

	public PersonalSummary getPersonalSummary() {
		return PersonalSummary;
	}

	public void setPersonalSummary(PersonalSummary personalSummary) {
		PersonalSummary = personalSummary;
	}

	public Individual() {

	}
	
	

}
