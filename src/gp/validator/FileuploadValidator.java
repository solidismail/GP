package gp.validator;

import gp.javabeans.FileUploadBean;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FileuploadValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
	
		return FileUploadBean.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		FileUploadBean file = (FileUploadBean) obj;
	
		}
	


}
