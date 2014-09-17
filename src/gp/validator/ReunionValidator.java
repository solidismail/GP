package gp.validator;

import gp.interfac.ICUser;
import gp.javabeans.Reunion;
import gp.javabeans.User;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;




public class ReunionValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		
		return Reunion.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
	
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date","error.not-specified-ReunionbeginDate","Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time","error.not-specified-ReunionTime","Required field");
		
		
		Reunion reu = (Reunion)obj;
		
		Date todaysDate= new Date(); 
		todaysDate.setHours(0);
		todaysDate.setMinutes(0);
		todaysDate.setSeconds(0);
		
		if ((reu.getDate()!=null)&&(reu.getTime()!=null)) {
			
		
		
		if ((reu.getDate().getYear()<todaysDate.getYear())||(((reu.getDate().getYear()==todaysDate.getYear()))&&((reu.getDate().getMonth()<todaysDate.getMonth())))||(((reu.getDate().getYear()==todaysDate.getYear()))&&((reu.getDate().getMonth()==todaysDate.getMonth()))&&(reu.getDate().getDate()<todaysDate.getDate()))
			){
			
			errors.rejectValue("date", "error.date-befortoday", null, "");
			
		}
		
		if (((reu.getDate().getYear()==todaysDate.getYear()))&&((reu.getDate().getMonth()==todaysDate.getMonth()))&&(reu.getDate().getDate()==todaysDate.getDate())&&(Time.valueOf(reu.getTime().concat(":00")).getHours()<=new Date().getHours())) {
			errors.rejectValue("time", "error.time-befornow", null, "");
		}
		
		}
		
		
	}



}
