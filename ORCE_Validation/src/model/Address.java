package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address
{
	@XmlElement(name = "AddressLine1")
    private String AddressLine1;
	
	@XmlElement(name = "Territory")
    private String Territory;

	@XmlElement(name = "PostalCode")
    private String PostalCode;

	@XmlElement(name = "City")
    private String City;

	@XmlElement(name = "Country")
    private String Country;

    public String getAddressLine1 ()
    {
        return AddressLine1;
    }

    public void setAddressLine1 (String AddressLine1)
    {
        this.AddressLine1 = AddressLine1;
    }

    public String getTerritory ()
    {
        return Territory;
    }

    public void setTerritory (String Territory)
    {
        this.Territory = Territory;
    }

    public String getPostalCode ()
    {
        return PostalCode;
    }

    public void setPostalCode (String PostalCode)
    {
        this.PostalCode = PostalCode;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    public String getCounty ()
    {
        return Country;
    }

    public void setCounty (String County)
    {
        this.Country = County;
    }

	public Address() {

	}
    
    

}