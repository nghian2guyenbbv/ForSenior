package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import model.Customer;
import model.Name;

public class DataFactory {
	public static final String title = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Customers>";
	public static final String lastLine = "</Customers>";
	public static final String addressOpenTag = "<AddressLine1>";
	public static final String addressCloseTag = "</AddressLine1>";
	public static final String emailOpenTag = "<EMailAddress>";
	public static final String emailCloseTag = "</EMailAddress>";
	public static final String countyOpenTag = "<County>";
	public static final String countyCloseTag = "</County>";
	public static final String countryOpenTag = "<Country>";
	public static final String countryCloseTag = "</Country>";
	public static final String firstNameOpenTag = "<Name Location=\"First\">";
	public static final String firstNameCloseTag = "</Name><Name Location=\"Middle\">";
	public static final String midNameOpenTag = "<Name Location=\"Middle\">";
	public static final String midNameCloseTag = "</Name><Name Location=\"Last\">";
	public static final String lastNameOpenTag = "<Name Location=\"Last\">";
	public static final String lastNameCloseTag = "</Name></Name>";
	public static final String postalCodeOpenTag= "<PostalCode>";
	public static final String postalCodeCloseTag= "</PostalCode>";

	public static Object buildModelFromXml(String argString, Class<?> argClass) throws JAXBException {
		Object model = new Object();
		JAXBContext context = JAXBContext.newInstance(argClass);
		Unmarshaller unmar = context.createUnmarshaller();
		StringReader reader = new StringReader(argString);
		model = unmar.unmarshal(reader);

		return model;
	}

	private static String replaceSpecialCharacter(String argText) {
		String result = argText;
		if (argText != null && !argText.isEmpty()) {
			String[] oldChar = { "\"", "\'", "<", ">", "&" };
			String[] newChar = { "&quot;", "&apos;", "&lt;", "&gt;", "&amp;" };
			for (int i = 0; i < oldChar.length; i++) {
				result = result.replace(oldChar[i], newChar[i]);
			}
		}
		return result;
	}

	private static String replaceStringEscape(String argLine, String argOpenTag, String argCloseTag) throws Exception {
		String line = StringUtils.EMPTY;
		int startIndex = argLine.indexOf(argOpenTag) + argOpenTag.length();
		int endIndex = argLine.indexOf(argCloseTag);
		String content = argLine.substring(startIndex, endIndex);;
		String fixedContent = replaceSpecialCharacter(content);
		line = argLine.replace(content, fixedContent);

		return line;
	}

	private static String fixLine(String argLine) throws Exception {
		String line = argLine.replace(countyOpenTag, countryOpenTag);
		line = line.replace(countyCloseTag, countryCloseTag);
		line = replaceStringEscape(line, addressOpenTag, addressCloseTag);
		line = replaceStringEscape(line, emailOpenTag, emailCloseTag);
		line = replaceStringEscape(line, firstNameOpenTag, firstNameCloseTag);
		line = replaceStringEscape(line, midNameOpenTag, midNameCloseTag);
		line = replaceStringEscape(line, lastNameOpenTag, lastNameCloseTag);
		line = replaceStringEscape(line, postalCodeOpenTag, postalCodeCloseTag);
		return line;
	}

	public static void readFileAndValidate(String[] errorListFileName) throws IOException {
		for (int idx = 0; idx < errorListFileName.length; idx++) {
			File resource = new File("C:\\temp_ori\\" + errorListFileName[idx] + "");
			if (!resource.exists()) {
				System.out.println(errorListFileName[idx] + " is not exists");
				continue;
			}
			System.out.println("checking " + errorListFileName[idx]);
			File target = new File("C:\\temp_fix\\" + errorListFileName[idx] + "");
			File error = new File("C:\\temp_error\\" + errorListFileName[idx] + "");
			error.createNewFile();
			target.createNewFile();
			FileWriter writer = new FileWriter("C:\\temp_fix\\" + errorListFileName[idx] + "");
			FileWriter errorWriter = new FileWriter("C:\\temp_error\\" + errorListFileName[idx] + "");

			BufferedWriter buffer = new BufferedWriter(writer);
			BufferedWriter errorBuffer = new BufferedWriter(errorWriter);
			// Creating Scanner instance to read File in Java
			Scanner scnr = null;
			try {
				scnr = new Scanner(resource);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Reading each line of the file using Scanner class
			int lineNumber = 1;
			while (scnr.hasNextLine()) {
				String line = scnr.nextLine();
				if (lineNumber == 1) {
					buffer.write(title);
					line = line.replace(title, "");
				}
				if (line.equals("</Customers>")) {
					break;
				}
				try {
					line = fixLine(line);
				} catch (Exception e) {
					errorBuffer.write(lineNumber + ": " + line + "\n");
					e.printStackTrace();
					continue;
				}
				
				boolean lineStatus = false;
				lineStatus = validateAndDelete(line);
				if (lineStatus == true) {
					buffer.write(line + "\n");
				} else {
					errorBuffer.write(lineNumber + ": " + line + "\n");
					System.out.println("[ERROR] " + errorListFileName[idx] + " : " + "lineNumber:" + lineNumber);
				}
				lineNumber++;
			}
			buffer.write(lastLine + "\n");
			errorBuffer.close();
			buffer.close();
		}

		System.out.println("DONE");

	}

	private static boolean validateAndDelete(String argString) {
		try {
			Customer customer = (Customer) buildModelFromXml(argString, Customer.class);
			return true;
		} catch (JAXBException e) {

			e.printStackTrace();
			return false;
		}

	}

}
