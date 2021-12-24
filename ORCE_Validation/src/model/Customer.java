package model;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
	
	@XmlAttribute(name = "Action")
	private String Action;
	
	@XmlElement(name = "EntityInformation")
	private EntityInformation EntityInformation;
	
	@XmlElement(name = "PersonalPreferences")
	private PersonalPreferences PersonalPreferences;
	
	@XmlElement(name = "AlternateKey")
	private AlternateKey AlternateKey;
	
	@XmlElement(name = "CustomAttribute")
	private CustomAttribute CustomAttribute;

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public EntityInformation getEntityInformation() {
		return EntityInformation;
	}

	public void setEntityInformation(EntityInformation entityInformation) {
		EntityInformation = entityInformation;
	}

	public PersonalPreferences getPersonalPreferences() {
		return PersonalPreferences;
	}

	public void setPersonalPreferences(PersonalPreferences personalPreferences) {
		PersonalPreferences = personalPreferences;
	}

	public AlternateKey getAlternateKey() {
		return AlternateKey;
	}

	public void setAlternateKey(AlternateKey alternateKey) {
		AlternateKey = alternateKey;
	}

	public CustomAttribute getCustomAttribute() {
		return CustomAttribute;
	}

	public void setCustomAttribute(CustomAttribute customAttribute) {
		CustomAttribute = customAttribute;
	}

	public Customer() {

	}
	

	
}
