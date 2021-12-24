package model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ContactPreference")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactPreference {
	
	@XmlAttribute(name = "ContactType")
	private String ContactType;
	
	@XmlAttribute(name = "Permission")
	private Boolean Permission;

	public String getContactType() {
		return ContactType;
	}

	public void setContactType(String ContactType) {
		this.ContactType = ContactType;
	}

	public Boolean getPermission() {
		return Permission;
	}

	public void setPermission(Boolean Permission) {
		this.Permission = Permission;
	}

	public ContactPreference() {

	}
	
	
}
