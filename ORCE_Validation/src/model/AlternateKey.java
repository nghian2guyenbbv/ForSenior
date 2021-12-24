package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AlternateKey")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlternateKey
{
	@XmlAttribute(name = "TypeCode")
    private String TypeCode;
	
	@XmlElement(name = "AlternateID")
    private String AlternateID;

    public String getTypeCode ()
    {
        return TypeCode;
    }

    public void setTypeCode (String TypeCode)
    {
        this.TypeCode = TypeCode;
    }

    public String getAlternateID ()
    {
        return AlternateID;
    }

    public void setAlternateID (String AlternateID)
    {
        this.AlternateID = AlternateID;
    }

	public AlternateKey() {

	}
    
    
}