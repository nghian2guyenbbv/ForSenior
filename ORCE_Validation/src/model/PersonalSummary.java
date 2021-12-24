package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PersonalSummary")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalSummary {
	
	@XmlElement(name = "GenderType")
	private String GenderType;

	public String getGenderType() {
		return GenderType;
	}

	public void setGenderType(String genderType) {
		GenderType = genderType;
	}

	public PersonalSummary() {

	}
	
	

}
