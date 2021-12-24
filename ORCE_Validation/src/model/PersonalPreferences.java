package model;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "PersonalPreferences")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalPreferences
{
	@XmlElement(name = "ContactPreference")
	private List<ContactPreference> ContactPreference;

	public List<ContactPreference> getContactPreference() {
		return ContactPreference;
	}

	public void setContactPreference(List<ContactPreference> contactPreference) {
		ContactPreference = contactPreference;
	}

	public PersonalPreferences() {

	}
	
	
    
}