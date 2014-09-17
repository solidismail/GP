package gp.validator;

import gp.javabeans.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
	/*
	 * private String login; private String pwd;
	 */

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	public boolean supports(Class clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login",
				"error.not-specified-login");
		if (user.getPwd().isEmpty()) {
			errors.rejectValue("pwd", "error.not-specified-pwd", null, "");
		}

		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(user.getLogin());
		

		
		if ( (! matcher.matches()) &&  (   (user.getLogin()).compareTo("admin")!=0 ) ) {
			errors.rejectValue("login", "error.validate-email", null, "");
		}
		
	}

}