package model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ContactInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactInformation {
	@XmlElement(name = "Address")
	private Address Address;

	@XmlElement(name = "Telephone")
	private Telephone Telephone;

	@XmlElement(name = "EMail")
	private EMail EMail;

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public Telephone getTelephone() {
		return Telephone;
	}

	public void setTelephone(Telephone telephone) {
		Telephone = telephone;
	}

	public EMail getEMail() {
		return EMail;
	}

	public void setEMail(EMail eMail) {
		EMail = eMail;
	}

	public ContactInformation() {

	}

}
