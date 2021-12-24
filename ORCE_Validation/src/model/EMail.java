package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EMail")
@XmlAccessorType(XmlAccessType.FIELD)
public class EMail {
	
	@XmlAttribute(name = "ContactPreferenceCode")
	private String ContactPreferenceCode;
	
	@XmlAttribute(name = "PrimaryFlag")
	private Boolean PrimaryFlag;
	
	@XmlAttribute(name = "TypeCode")
	private String TypeCode;
	
	@XmlElement(name = "EMailAddress")
	private String EMailAddress;

	public String getContactPreferenceCode() {
		return ContactPreferenceCode;
	}

	public void setContactPreferenceCode(String contactPreferenceCode) {
		ContactPreferenceCode = contactPreferenceCode;
	}

	public Boolean getPrimaryFlag() {
		return PrimaryFlag;
	}

	public void setPrimaryFlag(Boolean primaryFlag) {
		PrimaryFlag = primaryFlag;
	}

	public String getTypeCode() {
		return TypeCode;
	}

	public void setTypeCode(String typeCode) {
		TypeCode = typeCode;
	}

	public String getEMailAddress() {
		return EMailAddress;
	}

	public void setEMailAddress(String eMailAddress) {
		EMailAddress = eMailAddress;
	}

	public EMail() {

	}
	
	

}
