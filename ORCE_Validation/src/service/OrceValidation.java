package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class OrceValidation {

	public static boolean isValidEmail(String argEmail) {
		boolean allowLocal = false;
		boolean isValid = false;
		if (StringUtils.isEmpty(argEmail)) {
			return true;
		}


		if (EmailValidator.getInstance(allowLocal).isValid(argEmail) && isValidEmailFormat(argEmail)) {
			isValid = true;
		}
		
		return isValid;
	}

	private static boolean isValidEmailFormat(String argEmail) {

		Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(argEmail);

		return matcher.matches();
	}

	public static boolean isValidName(String argName) {

		if (StringUtils.isEmpty(argName)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[A-Za-z\\\\s]+$");
		Matcher matcher = pattern.matcher(argName);

		return matcher.matches();
	}

}
