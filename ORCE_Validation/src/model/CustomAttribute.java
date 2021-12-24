package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CustomAttribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomAttribute
{
	@XmlAttribute(name = "name")
    private String name;
	
	@XmlElement(name = "AttributeValue")
    private String AttributeValue;
	


    public String getAttributeValue ()
    {
        return AttributeValue;
    }

    public void setAttributeValue (String AttributeValue)
    {
        this.AttributeValue = AttributeValue;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

	public CustomAttribute() {

	}
    
    
}
