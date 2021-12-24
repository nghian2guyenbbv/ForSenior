package com.xmltojava;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToJavaOb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = new File("C:\\NOTEFORBTM\\question.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);
			Unmarshaller unmarsheller = jaxbContext.createUnmarshaller();
			Question question = (Question) unmarsheller.unmarshal(file);
			System.out.println("Question is:"+question.getQuestionname());
			System.out.println("Aswer 1 is: "+question.getAnswers().get(1).getAnswername());
			System.out.println("Answer 2 is: "+question.getAnswers().get(0).getAnswername());
			System.out.println("Success to unmarshall");
		} catch (JAXBException ex) {
			System.out.println("Error to read file");
		}
	}

}
