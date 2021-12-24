package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Telephone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Telephone {
	
	@XmlAttribute(name = "TypeCode")
	private String TypeCode;
	
	@XmlElement(name = "PhoneNumber")
	private String PhoneNumber;
	
	public String getTypeCode() {
		return TypeCode;
	}

	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}
	

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public Telephone() {

	}
	
	
}
