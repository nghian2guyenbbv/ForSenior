package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EntityInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityInformation {
	
	@XmlElement(name = "Individual")
	private Individual Individual;

	public Individual getIndividual() {
		return Individual;
	}

	public void setIndividual(Individual individual) {
		Individual = individual;
	}

	public EntityInformation() {

	}
	
}